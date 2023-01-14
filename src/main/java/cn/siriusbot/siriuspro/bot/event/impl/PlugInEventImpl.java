package cn.siriusbot.siriuspro.bot.event.impl;

import cn.siriusbot.siriuspro.bot.annotation.OnBotEvent;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.PlugInEvent;
import cn.siriusbot.siriuspro.bot.event.v1.EventMethodHaveParam;
import cn.siriusbot.siriuspro.bot.pojo.e.BotEventType;
import cn.siriusbot.siriuspro.bot.pojo.event.BotEventMessage;
import cn.siriusbot.siriuspro.config.bean.PlugInPool;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PlugInEventImpl implements PlugInEvent , EventMethodHaveParam<BotEventMessage> {

    BotClient client;
    PlugInPool pool;

    public PlugInEventImpl(PlugInPool pool) {
        this.pool = pool;
    }

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
        pool.putEvent(this.client.getInfo().getBotId(), body);
    }
}
