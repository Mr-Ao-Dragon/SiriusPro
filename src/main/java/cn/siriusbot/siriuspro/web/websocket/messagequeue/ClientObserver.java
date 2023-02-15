package cn.siriusbot.siriuspro.web.websocket.messagequeue;

public interface ClientObserver {

    /**
     * 发送消息到客户端
     * @param s 数据内容
     */
    void sendMsg(String s) throws Exception;


    void asyncSendMsg(String s);
}
