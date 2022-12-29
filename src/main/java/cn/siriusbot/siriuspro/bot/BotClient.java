package cn.siriusbot.siriuspro.bot;

import cn.siriusbot.siriuspro.websocket.SiriusWebSocketClient;
import okhttp3.OkHttpClient;

public interface BotClient {

    BotToken getInfo();

    BotSocket getSocket();

    SiriusWebSocketClient getWebSocketClient();

    void setWebSocketClient(SiriusWebSocketClient client);

    OkHttpClient getHttpClient();
}
