package cn.siriusbot.siriuspro.web.websocket;

import cn.siriusbot.siriuspro.bot.application.SiriusApplicationInfo;
import cn.siriusbot.siriuspro.bot.plugin.EPlugInClient;
import cn.siriusbot.siriuspro.bot.plugin.PlugInClient;
import cn.siriusbot.siriuspro.bot.plugin.PlugInFactory;
import cn.siriusbot.siriuspro.bot.pojo.event.BotEventMessage;
import cn.siriusbot.siriuspro.uitls.AppContextUtil;
import cn.siriusbot.siriuspro.web.R.R;
import cn.siriusbot.siriuspro.web.pojo.WebSocketBody;
import cn.siriusbot.siriuspro.web.websocket.messagequeue.ClientObserver;
import cn.siriusbot.siriuspro.web.websocket.messagequeue.ClientSubject;
import cn.siriusbot.siriuspro.web.websocket.messagequeue.ClientTask;
import cn.siriusbot.siriuspro.web.websocket.messagequeue.MsgQueue;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.Executor;

/**
 * 外部插件服务接口
 */
@Component
@ServerEndpoint(value = "/websocket")
@Log4j2
public class PlugInWebSocketServer implements ClientObserver{

    SiriusApplicationInfo info;

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    private PlugInFactory plugInFactory;
    private Executor executor;

    private PlugInClient plugInClient;

    private int verify = 0; // 验证状态 0 未验证 1 验证通过

    @OnOpen
    public void onOpen(Session session) {
        this.executor = AppContextUtil.getBean(Executor.class);
        this.plugInFactory = AppContextUtil.getBean(PlugInFactory.class);
        this.session = session;
        log.info(String.format("[ws] (%s)新客户端连接", this.session.getId()));
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

    @Override
    public void asyncSendMsg(String s) {
        this.session.getAsyncRemote().sendText(s);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println(message);
        try {
            WebSocketBody body = JSON.parseObject(message, WebSocketBody.class);
            switch (body.getCode()) {
                case 1 -> {
                    if (this.verify != 0){
                        break;
                    }
                    // 首次连接验证插件信息
                    SiriusApplicationInfo info = body.getBody().toJavaObject(SiriusApplicationInfo.class);
                    // 检验插件信息
                    if (info.getPackageName() == null) {
                        R r = new R()
                                .setCode(500)
                                .setMsg("插件包名不能为空!");
                        this.sendMsg(JSONObject.toJSONString(r));
                        return;
                    }
                    if (info.getAppName() == null) {
                        R r = new R()
                                .setCode(500)
                                .setMsg("插件名不能为空!");
                        this.sendMsg(JSONObject.toJSONString(r));
                        return;
                    }
                    if (info.getAppAuthor() == null) {
                        R r = new R()
                                .setCode(500)
                                .setMsg("插件作者不能为空!");
                        this.sendMsg(JSONObject.toJSONString(r));
                        return;
                    }
                    if (info.getAppPath() == null) {
                        R r = new R()
                                .setCode(500)
                                .setMsg("插件路径不能为空!");
                        this.sendMsg(JSONObject.toJSONString(r));
                        return;
                    }
                    if (info.getAppDesc() == null) {
                        info.setAppDesc("");
                    }
                    this.info = info;
                    this.plugInClient = new EPlugInClient(this, info, this.executor);
                    this.plugInFactory.add(
                            this.plugInClient
                    );
                    R r = new R()
                            .setCode(0)
                            .setMsg("插件验证通过");
                    this.sendMsg(JSONObject.toJSONString(r));
                    this.verify = 1;
                }
            }
        } catch (Exception e) {
            log.error("处理ws消息异常，嵌套异常为 -> " + e);
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
        this.plugInFactory.remove(this.plugInClient.getPackageName());

        log.info(String.format("[ws] (%s)客户端断开", this.session.getId()));
    }

}
