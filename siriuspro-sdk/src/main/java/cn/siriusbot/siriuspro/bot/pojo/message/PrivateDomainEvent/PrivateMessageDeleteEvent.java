package cn.siriusbot.siriuspro.bot.pojo.message.PrivateDomainEvent;

/**
 * 私域消息呗撤回事件
 */
public class PrivateMessageDeleteEvent {
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
    private PrivateMessageDeleteDObject d;

    /**
     * 事件ID
     */
    private String id;

    /**
     * 所属机器人ID
     */
    private String bot_id;
}
