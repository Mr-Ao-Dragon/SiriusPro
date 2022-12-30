package cn.siriusbot.siriuspro.message.GuildEvent;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
/**
 * 频道事件详情对象
 */
public class GuildDObject {
    /**
     * 操作人是否为频道主
     */
    private Boolean owner;

    /**
     * 操作人id
     */
    private String op_user_id;

    /**
     * 当前频道主ID
     */
    private String owner_id;

    /**
     * 频道图标链接
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
     * 不知道作用
     */
    private String union_appid;

    /**
     * 不知道作用
     */
    private String union_org_id;

    /**
     * 不知道作用
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
     * 当前频道人数
     */
    private Integer member_count;
}
