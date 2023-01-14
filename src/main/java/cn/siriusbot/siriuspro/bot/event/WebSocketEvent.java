package cn.siriusbot.siriuspro.bot.event;

import cn.siriusbot.siriuspro.bot.client.BotWebSocket;
import cn.siriusbot.siriuspro.bot.event.v1.BotEvent;

public interface WebSocketEvent extends BotEvent {

    /**
     * 获取BotWebSocket客户端
     * @return
     */
    BotWebSocket getWebSocket();

    /**
     * 重连
     */
    void reconnection();
}
