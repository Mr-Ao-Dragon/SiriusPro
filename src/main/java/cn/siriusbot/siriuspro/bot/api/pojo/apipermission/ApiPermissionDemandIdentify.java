package cn.siriusbot.siriuspro.bot.api.pojo.apipermission;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 接口权限需求标识对象
 */
@Data
@Accessors(chain = true)
public class ApiPermissionDemandIdentify {

    /**
     * API接口名,例如/guilds/{guild_id}/members/{user_id}
     */
    private String path;

    /**
     * 请求方法 例如GET
     */
    private String method;
}
