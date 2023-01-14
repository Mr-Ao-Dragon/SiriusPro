package cn.siriusbot.siriuspro.bot.pojo.message.ForumEvent;

import cn.siriusbot.siriuspro.bot.pojo.message.MessageBody;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 私域论坛消息事件
 */
@Data
@Accessors(chain = true)
public class ForumEvent implements MessageBody {
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
    private ForumEventDObject d;

    /**
     * 事件id
     */
    private String id;

    /**
     * 所属机器人ID
     */
    private String bot_id;

}
