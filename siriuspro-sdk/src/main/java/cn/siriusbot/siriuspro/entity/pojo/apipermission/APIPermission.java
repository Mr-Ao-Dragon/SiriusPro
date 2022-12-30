package cn.siriusbot.siriuspro.entity.pojo.apipermission;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 接口权限对象
 */
@Data
@Accessors(chain = true)
public class APIPermission {
    /**
     * 接口名称,例如/guilds/{guild_id}/members/{user_id}
     */
    private String path;

    /**
     * 请求方法,例如 GET
     */
    private String method;

    /**
     * Api接口名称,例如获取频道信息
     */
    private String desc;

    /**
     * 授权状态,auth_status为1时，代表已授权
     */
    private Integer auth_status;


}
