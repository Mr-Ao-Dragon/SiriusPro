package cn.siriusbot.siriuspro.bot;

import cn.siriusbot.siriuspro.timer.SiriusTimer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.net.URI;
import java.util.TimerTask;

@Data
@Accessors(chain = true)
public class BotSocket {
    /**
     * 用于openApi请求的url
     */
    String openUrl;

    /**
     * 理论上是消息顺序
     */
    int s;

    /**
     * 当前会话ID
     */
    String session_id;

    /**
     * 用于WebSocket客户端连接的URI
     */
    URI webSocketUri;

    /**
     * 心跳间隔,毫秒
     */
    int heartBeat;

    /**
     * 心跳定时器
     */
    SiriusTimer heartBeatTimer;

    /**
     * 心跳任务
     */
    TimerTask sendHeartBeat;
}
