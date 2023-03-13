package cn.siriusbot.siriuspro.admin.service.impl;

import cn.siriusbot.siriuspro.admin.service.LogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LogServiceImpl implements LogService {

    Map<String, Queue<String>> queueMap = new ConcurrentHashMap<>();
    Queue<String> cache = new ConcurrentLinkedQueue<>();
    AtomicInteger index = new AtomicInteger(0);

    @Override
    public List<String> getLogLately() {
        return new ArrayList<>(cache);
    }

    /**
     * 推入日志
     *
     * @param log
     */
    @Override
    public void pushLog(String log) {
        for (String key : queueMap.keySet()){
            queueMap.get(key).add(log);
        }
        cache.add(log);
        if (index.get() > 200){
            cache.poll();
        } else {
            index.addAndGet(1);
        }
    }

    /**
     * 注册监听
     *
     * @param sessionId
     */
    @Override
    public void registerListener(String sessionId) {
        queueMap.put(sessionId, new ConcurrentLinkedQueue<>());
    }

    /**
     * 删除监听
     *
     * @param sessionId
     */
    @Override
    public void deleteListener(String sessionId) {
        queueMap.remove(sessionId);
    }

    /**
     * 根据会话获取日志信息
     *
     * @param sessionId
     */
    @Override
    public List<String> getLogBySession(String sessionId) {
        List<String> result = new ArrayList<>();
        if (queueMap.containsKey(sessionId)){
            Queue<String> strings = queueMap.get(sessionId);
            while (!strings.isEmpty()){
                result.add(strings.poll());
            }
        }
        return result;
    }
}
