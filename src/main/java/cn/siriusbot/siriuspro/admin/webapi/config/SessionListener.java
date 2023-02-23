package cn.siriusbot.siriuspro.admin.webapi.config;

import cn.siriusbot.siriuspro.uitls.AppContextUtil;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

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
