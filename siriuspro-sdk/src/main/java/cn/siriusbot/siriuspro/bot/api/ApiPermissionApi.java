package cn.siriusbot.siriuspro.bot.api;


import cn.siriusbot.siriuspro.bot.api.pojo.apipermission.APIPermission;
import cn.siriusbot.siriuspro.bot.api.pojo.apipermission.ApiPermissionDemand;
import cn.siriusbot.siriuspro.bot.api.pojo.apipermission.ApiPermissionDemandIdentify;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;

import java.util.List;

/**
 * Api权限对象Api
 */
public interface ApiPermissionApi {

    /**
     * 创建频道Api接口权限，授权链接
     * @param bot_id 传入机器人ID
     * @param guild_id 频道ID
     * @param channel_id 子频道ID
     * @param api_identify Api权限需求标识对象
     * @param desc 机器人申请对于的API接口权限后，可使用功能的描述
     * @return Api接口权限需求对象
     */
    public abstract Tuple<ApiPermissionDemand,String> createApiGrantLink(String bot_id, String guild_id, String channel_id, ApiPermissionDemandIdentify api_identify, String desc);

    /**
     * 获取频道可用权限列表
     * @param bot_id 传入机器人ID
     * @param guild_id 频道ID
     * @return 返回可用Api权限对象列表
     */
    public abstract Tuple<List<APIPermission>,String> getAPIPermissions(String bot_id, String guild_id);
}
