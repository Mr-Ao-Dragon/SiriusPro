package cn.siriusbot.siriuspro.bot.pojo.message.GuildMemberEvent;

import cn.siriusbot.siriuspro.bot.pojo.message.MessageBody;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 频道成员事件消息对象
 */
@Accessors(chain = true)
@Data
public class GuildMemberEventInfo implements MessageBody {
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
     * 频道成员事件详情对象
     */
    private GuildMemberDObject d;

    /**
     * 事件id
     */
    private String id;

    /**
     * 所属机器人id
     */
    private String bot_id;
}
