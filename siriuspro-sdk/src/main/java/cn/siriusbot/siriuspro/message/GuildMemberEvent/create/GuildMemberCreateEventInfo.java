package cn.siriusbot.siriuspro.message.GuildMemberEvent.create;

/**
 * 频道成员加入事件
 */
public class GuildMemberCreateEventInfo {

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
