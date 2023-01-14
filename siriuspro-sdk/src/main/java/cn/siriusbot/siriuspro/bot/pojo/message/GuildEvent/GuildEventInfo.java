package cn.siriusbot.siriuspro.bot.pojo.message.GuildEvent;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 频道事件消息对象
 */
@Data
@Accessors(chain = true)
public class GuildEventInfo {

    /**
     * op值
     */
    private Integer op;

    /**
     * webSocketClient会话消息顺序
     */
    private Integer s;

    /**
     * 事件类型
     */
    private String t;

    /**
     * d字段
     */
    private GuildDObject d;

    /**
     * 事件id
     */
    private String id;

    /**
     * 所属机器人id
     */
    private String bot_id;
}
