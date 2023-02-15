package cn.siriusbot.siriuspro.bot.event.impl;

import cn.siriusbot.siriuspro.admin.entity.Robot;
import cn.siriusbot.siriuspro.bot.annotation.OnBotEvent;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.ExceptionEvent;
import cn.siriusbot.siriuspro.bot.event.WebSocketEvent;
import cn.siriusbot.siriuspro.bot.event.v1.EventMethodNoParam;
import cn.siriusbot.siriuspro.bot.pojo.e.BotEventType;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ExceptionEventImpl implements ExceptionEvent , EventMethodNoParam {

    BotClient client;
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


    @OnBotEvent
    @Override
    public void onEvent(BotEventType type) {
        switch (type){
            case SEND_HEART_BEAT_ERROR -> {
                // 心跳包发送异常 尝试重连
                this.client.getInfo().setState(Robot.STATE_ERROR); // 异常
                this.client.getInfo().setErrorInfo("发送心跳包异常，尝试重连");
                log.info("Bot[" + client.getInfo().getBotId() + "]发送心跳包失败，尝试重连...");
                WebSocketEvent bean = client.getBean(WebSocketEvent.class);
                bean.reconnection();    // 重连
            }
        }
    }
}
