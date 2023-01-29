package cn.siriusbot.siriuspro.web.websocket;

import cn.siriusbot.siriuspro.bot.application.SiriusApplicationInfo;
import cn.siriusbot.siriuspro.web.pojo.WebSocketBody;
import cn.siriusbot.siriuspro.web.websocket.messagequeue.ClientObserver;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * 外部插件服务接口
 */
@Component
@ServerEndpoint(value = "/websocket")
@Log4j2
public class PlugInWebSocketServer implements ClientObserver {

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
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

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) {
        WebSocketBody body = JSON.parseObject(message, WebSocketBody.class);
        switch (body.getCode()){
            case 1 -> {
                // 首次连接验证插件信息
                SiriusApplicationInfo info = new SiriusApplicationInfo();

            }
        }

    }


    /**
     * @param error 错误
     */
    @OnError
    public void onError(Throwable error) {

    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {

    }
}
