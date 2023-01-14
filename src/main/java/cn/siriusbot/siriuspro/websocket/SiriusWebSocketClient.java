package cn.siriusbot.siriuspro.websocket;

import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.pojo.message.MessageManager;
import com.alibaba.fastjson.JSONObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;


import java.net.URI;

public class SiriusWebSocketClient extends WebSocketClient {
    SiriusBotClient siriusBotClient;
    public SiriusWebSocketClient(SiriusBotClient siriusBotClient, URI serverUri) {
        super(serverUri);
        this.siriusBotClient = siriusBotClient;
    }
    public SiriusWebSocketClient(){
        super(null);

    }
    @Override
    public void onOpen(ServerHandshake serverHandshake) {

    }

    @Override
    public void onMessage(String s) {
        JSONObject json = JSONObject.parseObject(s);
        json.put("bot_id", siriusBotClient.getInfo().getBotId());
        MessageManager.messageHandle(siriusBotClient.getInfo().getBotId(), json.toJSONString());
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
