package cn.siriusbot.siriuspro.bot.pojo.message.OpenForumEvent;

import cn.siriusbot.siriuspro.bot.api.pojo.forum.thread.ForumThread;
import cn.siriusbot.siriuspro.bot.pojo.message.MessageBody;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 公域论坛消息事件
 */
@Data
@Accessors(chain = true)
public class OpenForumEventInfo implements MessageBody {
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
     * 事件详情
     */
    private ForumThread d;

    /**
     * 事件ID
     */
    private String id;

    /**
     * 所属机器人ID
     */
    private String bot_id;
}
