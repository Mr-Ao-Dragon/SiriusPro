package cn.siriusbot.siriuspro.bot;

import cn.siriusbot.siriuspro.entity.pojo.User;
import cn.siriusbot.siriuspro.websocket.SiriusWebSocketClient;
import okhttp3.OkHttpClient;

public class SiriusBotClient implements BotClient{

    BotToken token;
    BotSocket botSocket;

    /**
     * 用于请求openApi的http客户端
     */
    OkHttpClient httpClient = new OkHttpClient();

    /**
     * webSocketClient客户端
     */
    SiriusWebSocketClient webSocketClient = new SiriusWebSocketClient();

    User user;

    public SiriusBotClient() {
        this.botSocket = new BotSocket();
        this.token = new BotToken();
    }

    public SiriusBotClient(BotToken token) {
        this.botSocket = new BotSocket();
        this.token = token;
    }

    public SiriusBotClient(String botId, String token, BotToken.botType botType, boolean sandBox) {
        this.botSocket = new BotSocket();
        this.token = new BotToken()
                .setBotId(botId)
                .setToken(token)
                .setBotType(botType)
                .setSandBox(sandBox);
    }

    @Override
    public BotToken getInfo() {
        return token;
    }

    @Override
    public BotSocket getSocket() {
        return botSocket;
    }

    @Override
    public SiriusWebSocketClient getWebSocketClient() {
        return webSocketClient;
    }

    @Override
    public void setWebSocketClient(SiriusWebSocketClient client) {
        this.webSocketClient = client;
    }

    @Override
    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public User getUser() {
        return user;
    }

    public SiriusBotClient setUser(User user) {
        this.user = user;
        return this;
    }
}
