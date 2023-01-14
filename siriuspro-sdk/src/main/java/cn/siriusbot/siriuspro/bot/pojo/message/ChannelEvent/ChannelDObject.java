package cn.siriusbot.siriuspro.bot.pojo.message.ChannelEvent;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 子频道事件详情对象
 */
@Data
@Accessors(chain = true)
public class ChannelDObject {
    /**
     * 操作人id
     */
    private String op_user_id;

    /**
     * 频道创建人id
     */
    private String owner_id;

    /**
     * 子频道类型
     */
    private Integer type;

    /**
     * 子频道应用类型
     */
    private String application_id;

    /**
     * 子频道子类型
     */
    private Integer sub_type;

    /**
     * 子频道所属，子频道分组id
     */
    private String parent_id;

    /**
     * 子频道发言权限
     */
    private Integer speak_permission;

    /**
     * 机器人在此频道拥有的权限
     */
    private String permissions;

    /**
     * 所属频道ID
     */
    private String guild_id;

    /**
     * 创建
     */
    private String name;

    /**
     * 子频道ID
     */
    private String id;

    /**
     * 子频道排序
     */
    private Integer position;

    /**
     * 子频道私密类型
     */
    private Integer private_type;
}
