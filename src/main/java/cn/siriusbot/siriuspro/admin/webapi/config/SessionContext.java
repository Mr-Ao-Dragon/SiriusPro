package cn.siriusbot.siriuspro.admin.webapi.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionContext {

    Map<String, HttpSession> sessionMap = new ConcurrentHashMap<>();

    public synchronized void AddSession(HttpSession session) {
        if (session != null) {
            System.out.println(session.getId() + " -> session创建");
            sessionMap.put(session.getId(), session);
        }
    }

    public synchronized void DelSession(HttpSession session) {
        if (session != null) {
            System.out.println(session.getId() + " -> sessions删除");
            sessionMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String session_id) {
        if (session_id == null) return null;
        return sessionMap.get(session_id);
    }
}

