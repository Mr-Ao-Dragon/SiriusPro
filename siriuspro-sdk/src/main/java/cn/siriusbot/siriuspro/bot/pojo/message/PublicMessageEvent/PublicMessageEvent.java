package cn.siriusbot.siriuspro.bot.pojo.message.PublicMessageEvent;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 公域消息事件对象
 */
@Data
@Accessors(chain = true)
public class PublicMessageEvent {
    /**
     * op值
     */
    private Integer op;

    /**
     * webSocketClient消息排序
     */
    private Integer s;

    /**
     * 事件类型
     */
    private String t;

    /**
     * 事件对象详情
     */
    private PublicMessageEventDObject d;

    /**
     * 事件ID
     */
    private String id;

    /**
     * 所属机器人ID
     */
    private String bot_id;
}
