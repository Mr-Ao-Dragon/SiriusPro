package cn.siriusbot.siriuspro.config.bean;

import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.client.BotConfigBuilder;
import cn.siriusbot.siriuspro.bot.event.*;
import cn.siriusbot.siriuspro.bot.event.impl.*;
import cn.siriusbot.siriuspro.bot.plugin.PlugInFactory;
import cn.siriusbot.siriuspro.web.websocket.messagequeue.ClientSubject;
import cn.siriusbot.siriuspro.web.websocket.messagequeue.MsgQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BotConfig implements BotConfigBuilder {

    @Autowired
    ClientSubject staticPoll;

    @Autowired
    MsgQueue staticQueue;

    @Autowired
    PlugInFactory factory;

    @Autowired
    BotPool botPool;

    @Autowired
    StatisticsPool statisticsPool;


    /**
     * 首要配置事件类
     *
     * @return
     */
    @Override
    public BotHttpEvent getBotHttpEvent() {
        return new BotHttpEventImpl();
    }

    /**
     * 其他事件类添加
     *
     * @param client
     */
    @Override
    public void builder(BotClient client) {
        client
                .setConfig(WebSocketEvent.class, new WebSocketEventImpl())
                .setConfig(MessageEvent.class, new MessageEventImpl())
                .setConfig(HeartbeatEvent.class, new HeartbeatEventImpl())
                .setConfig(PlugInEvent.class, new PlugInEventImpl(factory))
                .setConfig(BotPoolEvent.class, new BotPoolEventImpl(botPool))
                .setConfig(ExceptionEvent.class, new ExceptionEventImpl())
                .setConfig(StatisticsEvent.class, new StatisticsEventImpl(statisticsPool));
    }
}
