package cn.siriusbot.siriuspro.config.bean;

import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.client.BotConfigBuilder;
import cn.siriusbot.siriuspro.bot.event.*;
import cn.siriusbot.siriuspro.bot.event.impl.*;
import cn.siriusbot.siriuspro.bot.event.v1.BotEvent;
import cn.siriusbot.siriuspro.websocket.messagequeue.ClientSubject;
import cn.siriusbot.siriuspro.websocket.messagequeue.MsgQueue;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BotConfig implements BotConfigBuilder {

    @Autowired
    ClientSubject staticPoll;

    @Autowired
    MsgQueue staticQueue;

    @Autowired
    PlugInPool plugInPool;


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
                .setConfig(PlugInEvent.class, new PlugInEventImpl(plugInPool))
                .setConfig(WebSocketServiceEvent.class, new WebSocketServiceEventImpl(this.staticPoll, this.staticQueue));
    }
}
