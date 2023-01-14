package cn.siriusbot.siriuspro.bot.api.pojo.role;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 修改后身份组对象
 */
@Data
@Accessors(chain = true)
public class NewRole {
    /**
     * 频道ID
     */
    private String guild_id;

    /**
     * 身份组ID
     */
    private String role_id;

    /**
     * 修改后的身份组
     */
    private Role role;
}
