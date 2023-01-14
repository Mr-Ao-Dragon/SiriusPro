package cn.siriusbot.siriuspro.bot.pojo.message.AudioMessageEvent;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 机器人上麦消息事件
 */
@Data
@Accessors(chain = true)
public class AudioMessageEvent {

    /**
     * op值
     */
    Integer op;

    /**
     * webSocketClient会话消息排序
     */
    Integer s;

    /**
     * 事件类型
     */
    String t;
    /**
     * 事件详情对象
     */
    AudioMessageEventDObject d;

    /**
     * 事件ID
     */
    String id;

    /**
     * 事件所属机器人ID
     */
    String bot_id;

}
