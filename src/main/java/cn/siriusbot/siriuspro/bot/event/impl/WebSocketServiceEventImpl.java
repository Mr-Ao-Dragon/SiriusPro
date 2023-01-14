package cn.siriusbot.siriuspro.bot.event.impl;

import cn.siriusbot.siriuspro.bot.annotation.OnBotEvent;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.WebSocketServiceEvent;
import cn.siriusbot.siriuspro.bot.event.v1.EventMethodHaveParam;
import cn.siriusbot.siriuspro.bot.pojo.e.BotEventType;
import cn.siriusbot.siriuspro.bot.pojo.event.BotEventMessage;
import cn.siriusbot.siriuspro.websocket.WebSocketServer;

public class WebSocketServiceEventImpl implements WebSocketServiceEvent, EventMethodHaveParam<BotEventMessage> {
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
    public void onEvent(BotEventType type, BotEventMessage body) {
        // todo 临时措施，不建议使用静态
        WebSocketServer.sendAll(body.getMessage());
    }
}
