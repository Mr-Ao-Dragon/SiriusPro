package cn.siriusbot.siriuspro.bot.event.impl;

import cn.siriusbot.siriuspro.bot.annotation.OnBotEvent;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.client.BotWebSocket;
import cn.siriusbot.siriuspro.bot.event.v1.EventMethodHaveParam;
import cn.siriusbot.siriuspro.bot.event.IntentsEvent;
import cn.siriusbot.siriuspro.bot.event.WebSocketEvent;
import cn.siriusbot.siriuspro.bot.pojo.e.BotEventType;
import cn.siriusbot.siriuspro.bot.pojo.e.IntentsType;
import cn.siriusbot.siriuspro.bot.pojo.event.BotWebSocketMessage;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class IntentsEventImpl implements IntentsEvent, EventMethodHaveParam<BotWebSocketMessage> {
    BotClient client;
    int intents = 0;

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
    public IntentsEvent setIntents(IntentsType intents) {
        this.intents = this.intents | intents.getVal();
        return this;
    }


    @OnBotEvent
    @Override
    public void onEvent(BotEventType type, BotWebSocketMessage body) {
        if (type == BotEventType.WEBSOCKET_MESSAGE && body.getOp() == 10) {
            BotWebSocket webSocket = this.client.getBean(WebSocketEvent.class).getWebSocket();
            if (!this.client.getSession().getSessionId().isEmpty()) {
                webSocket.send(getReconnectPack());  // 发送重连包
                return;
            }
            JSONObject d = body.getBody().getJSONObject("d");
            this.client.getSession().setHeartBeat(d.getInteger("heartbeat_interval"));
            webSocket.send(getAuthPack());  // 发送授权包
        }
    }

    /**
     * 生成鉴权包
     *
     * @return 返回用于鉴权的数据
     */
    private String getAuthPack() {
        JSONObject rootObject = new JSONObject();
        rootObject.put("op", 2);
        JSONObject dObject = new JSONObject();
        dObject.put("token", client.getInfo().getBotId() + "." + client.getInfo().getToken());
        dObject.put("intents", intents);
        dObject.put("shard", null);
        dObject.put("properties", null);
        rootObject.put("d", dObject);
        return rootObject.toJSONString();
    }

    /**
     * 生成重连包
     *
     * @return 返回用于重连的数据
     */
    private String getReconnectPack() {
        JSONObject json = new JSONObject();
        JSONObject dObject = new JSONObject();
        json.put("op", 6);
        dObject.put("token", this.client.getInfo().getBotId() + "." + this.client.getInfo().getToken());
        dObject.put("session_id", this.client.getSession().getSessionId());
        dObject.put("seq", 2);
        json.put("d", dObject);
        return json.toJSONString();
    }
}
