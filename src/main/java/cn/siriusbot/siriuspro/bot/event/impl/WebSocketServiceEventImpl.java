package cn.siriusbot.siriuspro.bot.event.impl;

import cn.siriusbot.siriuspro.bot.annotation.OnBotEvent;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.WebSocketServiceEvent;
import cn.siriusbot.siriuspro.bot.event.v1.EventMethodHaveParam;
import cn.siriusbot.siriuspro.bot.event.v1.EventMethodNoParam;
import cn.siriusbot.siriuspro.bot.pojo.e.BotEventType;
import cn.siriusbot.siriuspro.bot.pojo.event.BotEventMessage;
import cn.siriusbot.siriuspro.bot.pojo.event.BotWebSocketMessage;
import cn.siriusbot.siriuspro.websocket.WebSocketServer;
import cn.siriusbot.siriuspro.websocket.messagequeue.ClientSubject;
import cn.siriusbot.siriuspro.websocket.messagequeue.ClientTask;
import cn.siriusbot.siriuspro.websocket.messagequeue.MsgQueue;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;

public class WebSocketServiceEventImpl implements WebSocketServiceEvent, EventMethodHaveParam<BotWebSocketMessage> {
    BotClient client;

    ClientSubject staticPoll;
    MsgQueue staticQueue;

    public WebSocketServiceEventImpl(ClientSubject staticPoll, MsgQueue staticQueue) {
        this.staticPoll = staticPoll;
        this.staticQueue = staticQueue;
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


    @Override
    public void sendAll(String message) {
        message = EmojiParser.parseToAliases(message);
        ClientTask[] taskList = staticPoll.getTaskList(message);
        for (ClientTask task : taskList){
            staticQueue.push(task);
        }
    }

    @OnBotEvent
    @Override
    public void onEvent(BotEventType type, BotWebSocketMessage body) {
        if (type == BotEventType.WEBSOCKET_MESSAGE) {
            this.sendAll(body.getMessage());
        }
    }
}
