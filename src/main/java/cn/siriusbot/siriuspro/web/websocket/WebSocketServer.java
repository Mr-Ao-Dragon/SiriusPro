package cn.siriusbot.siriuspro.web.websocket;

import cn.siriusbot.siriuspro.uitls.AppContextUtil;
import cn.siriusbot.siriuspro.web.websocket.messagequeue.ClientObserver;
import cn.siriusbot.siriuspro.web.websocket.messagequeue.ClientSubject;
import cn.siriusbot.siriuspro.web.websocket.messagequeue.ClientTask;
import cn.siriusbot.siriuspro.web.websocket.messagequeue.MsgQueue;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@Component
@ServerEndpoint(value = "/websocketServer/{userId}")
@Log4j2
public class WebSocketServer implements ClientObserver {

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId;

    WebSocketServerPoll poll;

    ClientSubject clientSubject;


    public WebSocketServer () {
        this.poll = AppContextUtil.getBean(WebSocketServerPoll.class);
        this.clientSubject = AppContextUtil.getBean(ClientSubject.class);
        WebSocketServer.staticPoll = AppContextUtil.getBean(ClientSubject.class);
        WebSocketServer.staticQueue = AppContextUtil.getBean(MsgQueue.class);
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        if (poll.getWebSocketMap().containsKey(userId)) {
            // 重新连接,踢掉上次的连接信息
            WebSocketServer webSocketServer = poll.getWebSocketMap().get(userId);
            webSocketServer.onClose();
        }
        poll.put(userId, this);
        clientSubject.add(this);
        log.info("用户登录:" + userId);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (poll.getWebSocketMap().containsKey(userId)) {
            try {
                // 踢掉已经连接的人
                this.session.close();
                poll.remove(userId);
                clientSubject.remove(this);
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
        String errorMsg = "用户错误:" + userId + ",原因:" + error.getMessage();
        //sendMessage(errorMsg);
        log.error(errorMsg);
    }


    /**
     * 静态推送入口
     */
    private static ClientSubject staticPoll;
    private static MsgQueue staticQueue;
    public static void sendAll(String message) {
        if (staticPoll != null && staticQueue != null){
            message = EmojiParser.parseToAliases(message);
            ClientTask[] taskList = staticPoll.getTaskList(message);
            for (ClientTask task : taskList){
                staticQueue.push(task);
            }
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
     * 发送消息到客户端
     *
     * @param s 数据内容
     */
    @Override
    public void sendMsg(String s) throws Exception {
        this.session.getBasicRemote().sendText(s);
    }
}