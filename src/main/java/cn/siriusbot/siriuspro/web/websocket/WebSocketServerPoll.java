package cn.siriusbot.siriuspro.web.websocket;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class WebSocketServerPoll {
    private final ConcurrentMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    public void put(String key, WebSocketServer server){
        this.webSocketMap.put(key, server);
    }

    public void remove(String key){
        this.webSocketMap.remove(key);
    }

    @Async
    public void sendAll(String message) {
        for (String s : webSocketMap.keySet()) {
            try {
                webSocketMap.get(s).sendMessage(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ConcurrentMap<String, WebSocketServer> getWebSocketMap() {
        return webSocketMap;
    }
}
