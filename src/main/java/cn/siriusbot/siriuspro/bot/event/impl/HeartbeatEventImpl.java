package cn.siriusbot.siriuspro.bot.event.impl;

import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.annotation.OnBotEvent;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.client.BotWebSocket;
import cn.siriusbot.siriuspro.bot.event.HeartbeatEvent;
import cn.siriusbot.siriuspro.bot.event.WebSocketEvent;
import cn.siriusbot.siriuspro.bot.event.v1.EventMethodHaveParam;
import cn.siriusbot.siriuspro.bot.event.v1.EventMethodNoParam;
import cn.siriusbot.siriuspro.bot.pojo.e.BotEventType;
import cn.siriusbot.siriuspro.bot.pojo.event.BotWebSocketMessage;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Log4j2
public class HeartbeatEventImpl implements HeartbeatEvent , EventMethodNoParam , EventMethodHaveParam<BotWebSocketMessage> {
    BotClient client;
    Timer timer = new Timer();    // 定时任务
    TimerTask task;

    /**
     * 注入客户端对象，并初始化
     *
     * @param client
     */
    @Override
    public void init(BotClient client) {
        this.client = client;

    }

    /**
     * 机器人登录事件
     */
    @Override
    public void start() {
        WebSocketEvent bean = this.client.getBean(WebSocketEvent.class);
        BotWebSocket webSocket = bean.getWebSocket();
        this.task = new TimerTask() {
            @Override
            public void run() {
                log.info("Bot[" + client.getInfo().getBotId() + "]发送心跳包");
                webSocket.send(getHeartBeatPack());
            }
        };
    }

    /**
     * 生成心跳包
     *
     * @return 返回用于发送心跳包的数据
     */
    private String getHeartBeatPack() {
        JSONObject json = new JSONObject();
        json.put("op", 1);
        json.put("d", this.client.getSession().getS());
        return json.toJSONString();
    }

    /**
     * 运行任务
     */
    @Override
    public void run() {
        this.timer.schedule(task, new Date(), this.client.getSession().getHeartBeat());
    }

    /**
     * 暂停
     */
    @Override
    public void pause() {
        task.cancel();
    }


    @OnBotEvent
    @Override
    public void onEvent(BotEventType type) {
        switch (type){
            case TASK_HEARTBEAT_START -> {
                // 启动心跳包事件
                this.run();
            }
            case TASK_HEARTBEAT_PAUSE, BOT_CLOSE -> {
                // 停止心跳包事件
                this.pause();
            }
        }
    }

    @OnBotEvent
    @Override
    public void onEvent(BotEventType type, BotWebSocketMessage body) {
        if (type == BotEventType.WEBSOCKET_MESSAGE && body.getOp() == 7) {
            // 进行重连
            log.info("Bot[" + client.getInfo().getBotId() + "]进行重连");
            this.pause();
            WebSocketEvent bean = this.client.getBean(WebSocketEvent.class);
            bean.reconnection(); // 重连
        }
    }
}
