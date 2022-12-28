package cn.siriusbot.siriuspro.message.MessageReactionEvent;

import cn.siriusbot.siriuspro.entity.pojo.emoji.MessageReaction;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 表情表态事件对象
 */
@Data
@Accessors(chain = true)
public class ReactionEventInfo {
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
     * 表情表态事件对象详情
     */
    private MessageReaction d;

    /**
     * 事件id
     */
    private String id;

    /**
     * 所属机器人ID
     */
    private String bot_id;
}
