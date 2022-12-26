package cn.siriusbot.siriuspro.entity.impl.role;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
/**
 * 频道身份组列表对象
 */
public class GuildRoleList {
    /**
     * 频道ID
     */
    private  String guild_id;
    /**
     * 身份组列表
     */
    private List<Role> roles;
    /**
     * 默认分组上限
     */
    private String role_num_limit;
}
