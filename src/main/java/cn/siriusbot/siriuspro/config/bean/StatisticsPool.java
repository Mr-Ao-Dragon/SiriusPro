package cn.siriusbot.siriuspro.config.bean;

import cn.siriusbot.siriuspro.bot.plugin.PlugInFactory;
import cn.siriusbot.siriuspro.config.pojo.StatisticsData;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数据统计类
 */
@Component
public class StatisticsPool {
    // 项目启动时时间戳
    private static final long startTime;

    static {
        startTime = System.currentTimeMillis();
    }

    @Autowired
    BotPool botPool;

    @Autowired
    PlugInFactory plugInFactory;

    @Autowired
    BotServiceCache botServiceCache;

    // <msg_id, 时间戳>
    Map<String, Long> msgEventMap = new ConcurrentHashMap<>();

    // <包名, 响应次数>
    Map<String, AtomicInteger> response = new ConcurrentHashMap<>();

    public int queryResponseNumByPackageName(String packageName){
        AtomicInteger atomicInteger = response.get(packageName);
        if (atomicInteger != null){
            return atomicInteger.get();
        }
        return 0;
    }

    /**
     * 消息数量
     */
    AtomicInteger msgNum = new AtomicInteger();

    /**
     * 响应数量
     */
    AtomicInteger responseNum = new AtomicInteger();

    public void addMsgNum(int num) {
        msgNum.addAndGet(num);
    }

    /**
     * 推入消息ID等待检测是否响应
     */
    public void putEventResponseMsgId(String msgId) {
        msgEventMap.put(msgId, System.currentTimeMillis());
        detectionExpiration();
    }

    /**
     * 响应事件
     */
    public void responseEventByMsgId(String packageName, String msgId) {
        if (msgEventMap.containsKey(msgId)) {
            msgEventMap.remove(msgId);
            if (!response.containsKey(packageName)) {
                response.put(packageName, new AtomicInteger());
            }
            AtomicInteger atomicInteger = response.get(packageName);
            atomicInteger.addAndGet(1);
            responseNum.addAndGet(1);
        }
    }

    /**
     * 检测过期msg_id
     */
    private void detectionExpiration() {
        long t = System.currentTimeMillis();
        for (String msgId : msgEventMap.keySet()) {
            try {
                Long aLong = msgEventMap.get(msgId);
                if (t - aLong > 300000) {
                    msgEventMap.remove(msgId);
                }
            } catch (Exception ignored) {

            }
        }
    }

    public StatisticsData getStatisticsData() {
        long t = System.currentTimeMillis();
        int botNum = botServiceCache.getDatabaseBotCount();
        int onlineBotNum = botPool.getCount();
        // 获取CPU信息
        OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        int processors = Runtime.getRuntime().availableProcessors();
        // 获取内存总容量
        long totalMemorySize = mem.getTotalMemorySize();
        // 获取可用内存容量(剩余物理内存）
        long freeMemorySize = mem.getFreeMemorySize();
        // 空闲的交换容量
        double usedRAM = ((totalMemorySize - freeMemorySize) * 1.0 / totalMemorySize) * 100;
        Runtime runtime = Runtime.getRuntime();
        // java虚拟机中的内存总量，可用内存空间 单位为byte，默认为系统的1/64
        long totalMemory = runtime.totalMemory();
        // java 虚拟机中的空闲内存量 空闲空间 单位byte， 默认为系统的1/4
        long freeMemory = runtime.freeMemory();
        double usedRAMJava = ((totalMemory - freeMemory) * 1.0 / totalMemory) * 100;
        int responseNumVal = responseNum.get();
        int msgNumVal = msgNum.get();

        StatisticsData statisticsData = new StatisticsData()
                .setBotNum(botNum)
                .setPlugInNUm(plugInFactory.getCount())
                .setMsgNum(msgNum.get())
                .setRunTime(t - startTime)
                .setOnLineRate((double) onlineBotNum / botNum * 100)
                .setMessageResponseRate((double) responseNumVal / msgNumVal  * 100)
                .setCpuUsage(mem.getCpuLoad() * 100)
                .setMemoryUsage(usedRAM)
                .setVirtualMemoryUsage(usedRAMJava);
        List<String> title = statisticsData.getTitle();
        title.add(String.format("%d 个 / %d 个", onlineBotNum, botNum));
        title.add(String.format("%d 条 / %d 条", responseNumVal, msgNumVal));
        title.add(String.format("%d 核", processors));
        title.add(String.format("%d M / %d M", freeMemorySize / 1048576L, totalMemorySize / 1048576L));
        title.add(String.format("%d M / %d M", freeMemory / 1048576L, totalMemory / 1048576L));
        return statisticsData;
    }

}
