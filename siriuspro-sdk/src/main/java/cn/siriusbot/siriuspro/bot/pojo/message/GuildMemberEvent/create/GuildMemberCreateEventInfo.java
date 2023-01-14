package cn.siriusbot.siriuspro.bot.pojo.message.GuildMemberEvent.create;

import cn.siriusbot.siriuspro.bot.pojo.message.MessageBody;

/**
 * 频道成员加入事件
 */
public class GuildMemberCreateEventInfo implements MessageBody {

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
    private GuildMemberCreateDObject d;

    /**
     * 事件ID
     */
    private String id;

    /**
     * 所属机器人ID
     */
    private String bot_id;
}
