package cn.siriusbot.siriuspro.admin.webapi.config;

import cn.siriusbot.siriuspro.uitls.AppContextUtil;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;


@WebListener
public class SessionListener implements HttpSessionListener {

    SessionContext sessionContext;

    private SessionContext getSessionContext(){
        if (sessionContext == null){
            sessionContext = AppContextUtil.getBean(SessionContext.class);
        }
        return sessionContext;
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        SessionContext sessionContext = getSessionContext();
        sessionContext.AddSession(httpSessionEvent.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        SessionContext sessionContext = getSessionContext();
        HttpSession session = httpSessionEvent.getSession();
        sessionContext.DelSession(session);
    }

}
