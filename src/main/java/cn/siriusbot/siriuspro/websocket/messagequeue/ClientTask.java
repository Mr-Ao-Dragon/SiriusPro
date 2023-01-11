package cn.siriusbot.siriuspro.websocket.messagequeue;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ClientTask {
    ClientObserver observer;
    String msg;

    /**
     * 重发次数
     */
    int retry;
}
