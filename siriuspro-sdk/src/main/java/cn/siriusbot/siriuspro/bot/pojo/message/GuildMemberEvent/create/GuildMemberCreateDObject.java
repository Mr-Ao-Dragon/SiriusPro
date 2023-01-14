package cn.siriusbot.siriuspro.bot.pojo.message.GuildMemberEvent.create;

/**
 * 事件详情对象
 */
public class GuildMemberCreateDObject {

    /**
     * 操作人是否为创建者
     */
    private Boolean owner;

    /**
     * 操作人id
     */
    private String op_user_id;

    /**
     * 频道主ID
     */
    private String owner_id;

    /**
     * 频道图标
     */
    private String icon;

    /**
     * 频道介绍
     */
    private String description;

    /**
     * 频道创建时间
     */
    private String joined_at;

    /**
     * 不知道干嘛的
     */
    private String union_appid;

    /**
     * 不知道干嘛的
     */
    private String union_org_id;

    /**
     * 不知道干嘛的
     */
    private String union_world_id;

    /**
     * 频道人数上限
     */
    private Integer max_members;

    /**
     * 频道ID
     */
    private String id;

    /**
     * 频道当前人数
     */
    private Integer member_count;
}
