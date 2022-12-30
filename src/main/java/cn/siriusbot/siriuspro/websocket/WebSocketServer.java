package cn.siriusbot.siriuspro.websocket;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@ServerEndpoint(value = "/websocketServer/{userId}")
@Log4j2
public class WebSocketServer {
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static final ConcurrentMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;

        if (webSocketMap.containsKey(userId)) {
            // 重新连接,踢掉上次的连接信息
            WebSocketServer webSocketServer = webSocketMap.get(userId);
            webSocketServer.onClose();
        }
        webSocketMap.put(userId, this);
        log.info("用户登录:" + userId);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userId)) {
            try {
                // 踢掉已经连接的人
                this.session.close();
                webSocketMap.remove(userId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("用户退出:" + userId);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("用户消息:" + userId + ",报文:" + message);
    }

    /**
     * @param error 错误
     */
    @OnError
    public void onError(Throwable error) {
        try {
            String errorMsg = "用户错误:" + userId + ",原因:" + error.getMessage();
            sendMessage(errorMsg);
            log.error(errorMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现服务器主动推送-string
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 实现服务器主动推送-object
     */
    public void sendMessage(Object message) throws EncodeException, IOException {
        this.session.getBasicRemote().sendObject(message);
    }

    /**
     * 发送自定义消息
     */
    public static String sendInfo(String userId, String message) throws IOException {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).sendMessage(message);
            return "发送成功";
        }
        String errorMsg = "用户" + userId + ",不在线！";
        log.info(errorMsg);
        return errorMsg;
    }

}