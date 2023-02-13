package cn.siriusbot.siriuspro.bot.plugin;

import cn.siriusbot.siriuspro.web.websocket.surface.WebsocketSession;

import javax.websocket.Session;

/**
 * 拓展功能
 */
public interface ExpandClient {
    /**
     * 推送ws消息
     */
    void putWebSocketMessage(WebsocketSession session, String message);
}
