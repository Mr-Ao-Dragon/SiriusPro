package cn.siriusbot.siriuspro.bot.event.impl;

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
        WebSocketEvent bean = this.client.getBean(WebSocketEvent.class);
        this.task = new TimerTask() {
            @Override
            public void run() {
                BotWebSocket webSocket = bean.getWebSocket();
                log.info("Bot[" + client.getInfo().getBotId() + "]发送心跳包");
                try{
                    webSocket.send(getHeartBeatPack());
                }catch (Exception e){
                    pause();
                    e.printStackTrace();
                    client.pushEvent(BotEventType.SEND_HEART_BEAT_ERROR,null);
                }
            }
        };
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
        if (type == BotEventType.WEBSOCKET_MESSAGE && body.getOp() == 9) {
            if (!this.client.getSession().getSessionId().isEmpty()){
                // 会话失效，进行重连
                this.client.getSession().setSessionId("");
                this.client.getSession().setS(0);
                log.info("Bot[" + client.getInfo().getBotId() + "]会话失效，重连重新授权");
                this.pause();
                WebSocketEvent bean = this.client.getBean(WebSocketEvent.class);
                bean.reconnection(); // 重连
            } else {
                log.error("Bot[" + client.getInfo().getBotId() + "]连接失败或会话上限，请检查配置。");
                this.client.pushEvent(BotEventType.WEBSOCKET_ERROR, null);
            }

        }
    }
}
