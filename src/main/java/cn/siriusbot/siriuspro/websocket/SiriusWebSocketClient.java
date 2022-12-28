package cn.siriusbot.siriuspro.websocket;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.message.MessageManager;
import com.alibaba.fastjson.JSONObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;


import java.net.URI;

public class SiriusWebSocketClient extends WebSocketClient {
    Bot bot;
    public SiriusWebSocketClient(Bot bot, URI serverUri) {
        super(serverUri);
        this.bot = bot;
    }
    public SiriusWebSocketClient(){

    }
    @Override
    public void onOpen(ServerHandshake serverHandshake) {

    }

    @Override
    public void onMessage(String s) {
        JSONObject json = JSONObject.parseObject(s);
        json.put("bot_id",bot.getBotId());
        MessageManager.messageHandle(bot.getBotId(), json.toJSONString());
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println(s);
    }

    @Override
    public void onError(Exception e) {
        System.out.println(e);
        e.printStackTrace();
    }
}
