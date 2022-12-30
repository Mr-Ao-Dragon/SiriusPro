package cn.siriusbot.siriuspro.message.ChannelEvent;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 子频道事件消息详情对象
 */
@Data
@Accessors(chain = true)
public class ChannelEventInfo {

    /**
     * op值
     */
    private  Integer op;

    /**
     * webSocketClient消息排序
     */
    private Integer s;

    /**
     * 事件类型
     */
    private String t;

    /**
     * 事件详情对象
     */
    private ChannelDObject d;

    /**
     * 事件id
     */
    private String id;
    /**
     * 事件所属机器人ID
     */
    private String bot_id;
}
