package cn.siriusbot.siriuspro.message.DirectMessageEvent;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 私信消息事件对象
 */
@Data
@Accessors(chain = true)
public class DirectMessageEventInfo {
    /**
     * op值
     */
    private Integer op;

    /**
     * webSocketClient消息排序
     */
    private Integer s;

    /**
     * 消息对象
     */
    private DirectMessageDObject d;

    /**
     * 事件ID
     */
    private String id;

    /**
     * 所属机器人ID
     */
    private String bot_id;
}
