package cn.siriusbot.siriuspro.entity.impl.apipermission;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.ApiPermissionApi;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.List;

/**
 * 接口权限对象
 */
@Data
@Accessors(chain = true)
public class APIPermission implements ApiPermissionApi {
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

    /**
     * 创建频道Api接口权限，授权链接
     *
     * @param bot          传入机器人对象
     * @param guild_id     频道ID
     * @param channel_id   子频道ID
     * @param api_identify Api权限需求标识对象
     * @param desc         机器人申请对于的API接口权限后，可使用功能的描述
     * @return Api接口权限需求对象
     */
    @SneakyThrows
    @Override
    public ApiPermissionDemand createApiGrantLink(Bot bot, String guild_id, String channel_id, ApiPermissionDemandIdentify api_identify, String desc) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl()+"guilds/"+guild_id+"/api_permission/demand").build();
        JSONObject json = new JSONObject();
        MediaType mediaType = MediaType.parse("application/json;text/plain");
        json.put("channel_id",channel_id);
        json.put("api_identify",api_identify);
        json.put("desc",desc);
        RequestBody body = RequestBody.create(mediaType,json.toJSONString());
        return JSONObject.parseObject(SiriusHttpUtils.postRequest(bot,request,body).body().string(),ApiPermissionDemand.class);
    }

    /**
     * 获取频道可用权限列表
     *
     * @param bot      传入机器人对象
     * @param guild_id 频道ID
     * @return 返回可用Api权限对象列表
     */
    @SneakyThrows
    @Override
    public List<APIPermission> getAPIPermissions(Bot bot, String guild_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl()+"guilds/"+guild_id+"/api_permission").build();
        JSONObject json = JSONObject.parseObject(SiriusHttpUtils.getRequest(bot,request).body().string());
        return json.getObject("apis",List.class);
    }
}
