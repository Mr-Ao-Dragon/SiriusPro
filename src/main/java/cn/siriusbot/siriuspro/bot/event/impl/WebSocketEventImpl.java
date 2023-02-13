package cn.siriusbot.siriuspro.bot.event.impl;

import cn.siriusbot.siriuspro.bot.annotation.OnBotEvent;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.client.BotWebSocket;
import cn.siriusbot.siriuspro.bot.client.BotWebSocketClient;
import cn.siriusbot.siriuspro.bot.event.WebSocketEvent;
import cn.siriusbot.siriuspro.bot.event.v1.EventMethodNoParam;
import cn.siriusbot.siriuspro.bot.pojo.e.BotEventType;
import cn.siriusbot.siriuspro.bot.pojo.event.BotWebSocketMessage;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

@Log4j2
public class WebSocketEventImpl implements WebSocketEvent, EventMethodNoParam {

    BotClient client;
    BotWebSocket webSocket;


    private static class BotWebSocketClientImpl extends BotWebSocketClient {
        BotClient client;

        /**
         * Constructs a WebSocketClient instance and sets it to the connect to the specified URI. The
         * channel does not attampt to connect automatically. The connection will be established once you
         * call <var>connect</var>.
         *
         * @param serverUri the server URI to connect to
         */
        public BotWebSocketClientImpl(URI serverUri, BotClient client) {
            super(serverUri);
            this.client = client;
        }

        @Override
        public void onOpen(ServerHandshake handshakedata) {

        }

        @Override
        public void onMessage(String message) {
            JSONObject json = JSONObject.parseObject(message);
            json.put("bot_id", client.getInfo().getBotId());
            message = json.toJSONString();
            client.pushEvent(BotEventType.WEBSOCKET_MESSAGE, new BotWebSocketMessage(
                    json.getInteger("op"), message, json));
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {

        }

        @Override
        public void onError(Exception ex) {
            log.error(ex);
        }
    }

    ;

    /**
     * 注入客户端对象，并初始化，在BotClient的start方法中会调用此方法
     *
     * @param client
     */
    @Override
    public void init(BotClient client) {
        this.client = client;
        this.webSocket = new BotWebSocketClientImpl(this.client.getSession().getWebSocketUri(), client);
    }

    /**
     * 机器人登录事件
     */
    @Override
    public void start() {
        try {
            this.webSocket.connect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 获取BotWebSocket客户端
     *
     * @return
     */
    @Override
    public BotWebSocket getWebSocket() {
        if (this.webSocket == null) {
            throw new NullPointerException();
        }
        return this.webSocket;
    }

    /**
     * 重连
     */
    @Override
    public void reconnection() {
        try {
            this.webSocket.close();
        } catch (Throwable ignored) {

        }
        this.webSocket = new BotWebSocketClientImpl(this.client.getSession().getWebSocketUri(), client);
        this.webSocket.connect();
    }


    @OnBotEvent
    @Override
    public void onEvent(BotEventType type) {
        if (type == BotEventType.BOT_CLOSE) {
            // 退出机器人
            this.webSocket.close();
        }
    }
}
