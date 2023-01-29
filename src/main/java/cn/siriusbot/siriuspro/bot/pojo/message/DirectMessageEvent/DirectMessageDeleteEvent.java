package cn.siriusbot.siriuspro.bot.pojo.message.DirectMessageEvent;

/**
 * 私信消息撤回事件对象
 */

import cn.siriusbot.siriuspro.bot.pojo.message.MessageBody;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DirectMessageDeleteEvent implements MessageBody {

    /**
     * op值
     */
    private Integer op;

    /**
     * 会话级事件排序
     */
    private Integer s;

    /**
     * 事件类型
     */
    private String t;

    /**
     * 事件ID
     */
    private String id;

    /**
     * 事件详情对象
     */
    private DirectMessageDeleteDObject d;

    /**
     * 事件所属机器人ID
     */
    private String bot_id;

}
