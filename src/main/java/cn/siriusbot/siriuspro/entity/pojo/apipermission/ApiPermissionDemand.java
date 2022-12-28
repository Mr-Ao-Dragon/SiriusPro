package cn.siriusbot.siriuspro.entity.pojo.apipermission;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 接口权限需求对象
 */
@Data
@Accessors(chain = true)
public class ApiPermissionDemand {

    /**
     * 申请接口权限的频道ID
     */
    private String guild_id;

    /**
     * 接口权限需求授权链接发送的子频道ID
     */
    private String channel_id;

    /**
     * 权限接口唯一标识
     */
    private ApiPermissionDemandIdentify api_identify;

    /**
     * 接口权限链接中的接口权限描述信息
     */
    private String title;

    /**
     * 接口权限链接中的机器人可使用给你的描述信息
     */
    private String desc;


}
