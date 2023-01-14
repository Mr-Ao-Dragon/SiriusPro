package cn.siriusbot.siriuspro.bot.pojo.message.InterActionMessageEvent;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 按钮回调消息事件对象
 */
@Data
@Accessors(chain = true)
public class InterActionEvent {
    /**
     * op值
     */
    private Integer op;

    /**
     * WebSocketClient消息排序
     */
    private Integer s;

    /**
     * 事件类型
     */
    private String t;

    /**
     * 事件对象详情
     */
    private InterActionEventDObject d;

    /**
     * 事件id
     */
    private String id;

    /**
     * 所属机器人ID
     */
    private String bot_id;
}
