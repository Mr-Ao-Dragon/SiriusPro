package cn.siriusbot.siriuspro.websocket.messagequeue;

import java.io.IOException;

public interface ClientObserver {

    /**
     * 发送消息到客户端
     * @param s 数据内容
     */
    void sendMsg(String s) throws Exception;
}
