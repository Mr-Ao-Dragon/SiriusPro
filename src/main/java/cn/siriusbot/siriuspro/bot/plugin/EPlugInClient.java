package cn.siriusbot.siriuspro.bot.plugin;

import cn.siriusbot.siriuspro.bot.application.SiriusApplicationInfo;
import cn.siriusbot.siriuspro.bot.pojo.event.BotEventMessage;
import cn.siriusbot.siriuspro.web.R.R;
import cn.siriusbot.siriuspro.web.websocket.messagequeue.ClientObserver;
import cn.siriusbot.siriuspro.web.websocket.messagequeue.ClientTask;
import cn.siriusbot.siriuspro.web.websocket.messagequeue.MsgQueue;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

/**
 * 易语言插件处理
 */
@Log4j2
public class EPlugInClient implements PlugInClient {

    @Data
    @Accessors(chain = true)
    private static class EventTask {

        String botId;
        /**
         * 推送消息
         */
        BotEventMessage message;

        /**
         * 重发次数
         */
        int retry;
    }

    Queue<EventTask> tasks = new ConcurrentLinkedQueue<>();

    int resourceNumber = 0;

    ClientObserver observer;
    SiriusApplicationInfo info; // 插件信息
    Executor executor; // 插件信息

    public EPlugInClient(ClientObserver observer, SiriusApplicationInfo info, Executor executor) {
        this.observer = observer;
        this.info = info;
        this.executor = executor;
    }

    /**
     * 插件唯一id
     */
    @Override
    public String getPackageName() {
        return this.info.getPackageName();
    }

    /**
     * 插件详细
     */
    @Override
    public SiriusApplicationInfo getInfo() {
        return this.info;
    }

    /**
     * 推送事件
     *
     * @param botId   机器人id
     * @param message 事件对象
     */
    @Override
    public synchronized void putEvent(String botId, BotEventMessage message) {
        tasks.add(
                new EventTask()
                        .setBotId(botId)
                        .setMessage(message)
                        .setRetry(0)
        );
        synchronized (this) {
            if (resourceNumber == 0) {
                resourceNumber++;
                executor.execute(() -> {
                    // 消费直到队列为空
                    while (!tasks.isEmpty()) {
                        EventTask poll = tasks.poll();
                        try {
                            EPlugInClient.this.observer.sendMsg(poll.getMessage().getMessage());  // 发送数据
                        } catch (Exception e) {
                            log.error(e);
                            poll.setRetry(poll.getRetry() + 1);
                            if (poll.getRetry() < 6) {
                                // 最多重发5次
                                EPlugInClient.this.putEvent(poll.getBotId(), poll.getMessage());
                            }
                        }
                    }
                    synchronized (EPlugInClient.this) {
                        resourceNumber--;
                    }
                });
            }
        }

    }

    /**
     * 插件web请求处理
     *
     * @param name 事件名称
     * @param body 请求json体
     * @return R对象
     */
    @Override
    public R webPost(String name, JSONObject body) {
        return null;
    }
}
