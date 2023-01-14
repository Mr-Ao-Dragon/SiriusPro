package cn.siriusbot.siriuspro.bot.pojo.message.PrivateDomainEvent;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 私域消息事件消息对象
 */
@Data
@Accessors
public class PrivateDomainMessageInfo {

    /**
     * op值
     */
    private Integer op;

    /**
     * 本次webSocketClient会话消息排序
     */
    private Integer s;

    /**
     * 事件类型
     */
    private String t;

    /**
     * d字段
     */
    private PrivateDObject d;

    /**
     * 本次事件的ID
     */
    private String id;

    /**
     * 所属机器人ID
     */
    private String bot_id;

}
