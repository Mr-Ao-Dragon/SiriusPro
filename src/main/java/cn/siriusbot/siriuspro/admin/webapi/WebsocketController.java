package cn.siriusbot.siriuspro.admin.webapi;

import cn.siriusbot.siriuspro.admin.entity.Admin;
import cn.siriusbot.siriuspro.admin.webapi.config.GetHttpSessionConfigurator;
import cn.siriusbot.siriuspro.config.Constant;
import cn.siriusbot.siriuspro.error.MsgException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Component
@ServerEndpoint(value = "/dash-board", configurator = GetHttpSessionConfigurator.class)
@Log4j2
public class WebsocketController {

    Session session;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        // 检测权限
        Admin admin = (Admin) httpSession.getAttribute(Constant.SESSION_ADMIN);
        if (admin == null){
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
