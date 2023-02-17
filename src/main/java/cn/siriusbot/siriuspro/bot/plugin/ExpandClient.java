package cn.siriusbot.siriuspro.bot.plugin;

import cn.siriusbot.siriuspro.web.websocket.surface.WebsocketSession;

import javax.websocket.Session;

/**
 * 拓展功能
 */
public interface ExpandClient {

    /**
     * 推送ws连接事件
     * @param session
     */
    void putWebSocketOpen(WebsocketSession session);

    /**
     * 推送ws消息事件
     */
    void putWebSocketMessage(WebsocketSession session, String message);

    /**
     * 推送ws关闭事件
     * @param session
     */
    void putWebSocketClose(WebsocketSession session);
}
