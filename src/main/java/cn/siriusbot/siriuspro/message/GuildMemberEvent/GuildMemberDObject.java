package cn.siriusbot.siriuspro.message.GuildMemberEvent;

import cn.siriusbot.siriuspro.entity.pojo.User;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 频道成员事件详情对象
 */
@Data
@Accessors(chain = true)
public class GuildMemberDObject {
    /**
     * 成员加入频道时间
     */
    private String joined_at;

    /**
     * 成员名称
     */
    private String nick;

    /**
     * 操作人id
     */
    private String op_user_id;

    /**
     * 成员身份组列表
     */
    private List<String> roles;

    /**
     * 频道ID
     */
    private String guild_id;

    /**
     * 成员用户信息
     */
    private User user;
}
