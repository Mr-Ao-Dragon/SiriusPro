package cn.siriusbot.siriuspro.bot.event.impl;

import cn.siriusbot.siriuspro.bot.annotation.OnBotEvent;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.PlugInEvent;
import cn.siriusbot.siriuspro.bot.event.v1.EventMethodHaveParam;
import cn.siriusbot.siriuspro.bot.pojo.e.BotEventType;
import cn.siriusbot.siriuspro.bot.pojo.event.BotEventMessage;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PlugInEventImpl implements PlugInEvent , EventMethodHaveParam<BotEventMessage> {

    static {

    }
    /**
     * 注入客户端对象，并初始化
     *
     * @param client
     */
    @Override
    public void init(BotClient client) {

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
        log.info(body);
    }
}
