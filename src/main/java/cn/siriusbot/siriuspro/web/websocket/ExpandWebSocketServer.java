package cn.siriusbot.siriuspro.web.websocket;

import cn.siriusbot.siriuspro.bot.plugin.PlugInFactory;
import cn.siriusbot.siriuspro.uitls.AppContextUtil;
import cn.siriusbot.siriuspro.web.websocket.surface.WebsocketSession;
import cn.siriusbot.siriuspro.web.websocket.surface.WebsocketSessionImpl;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;



/**
 * 拓展WebSocket
 */
@Component
@ServerEndpoint(value = "/expand-websocket")
@Log4j2
public class ExpandWebSocketServer {

    PlugInFactory factory;

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    private WebsocketSession websocketSession;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        this.websocketSession = new WebsocketSessionImpl(session);
        this.factory = AppContextUtil.getBean(PlugInFactory.class);
        this.factory.putExpandWebSocketOpen(this.websocketSession);
    }


    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) {
        factory.putExpandWebSocketEvent(websocketSession, message);
    }

    @OnClose
    public void onClose() {
        this.factory.putExpandWebSocketClose(this.websocketSession);
    }
}
