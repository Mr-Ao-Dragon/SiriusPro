package cn.siriusbot.siriuspro.message.AuditMessageEvent;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 消息审核事件对象
 */
@Data
@Accessors(chain = true)
public class AuditMessageEvent {

    /**
     * op值
     */
    Integer op;

    /**
     * webSocketClient消息排序
     */
    Integer s;
    /**
     * 事件类型
     */
    String t;

    /**
     * 事件详情对象
     */
    AuditMessageEventDObject d;

    /**
     * 事件ID
     */
    String id;

    /**
     * 事件所属机器人id
     */
    String bot_id;
}
