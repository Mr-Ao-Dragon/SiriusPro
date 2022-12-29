package cn.siriusbot.siriuspro.entity.api.impl;

import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.ApiPermissionApi;
import cn.siriusbot.siriuspro.entity.pojo.apipermission.APIPermission;
import cn.siriusbot.siriuspro.entity.pojo.apipermission.ApiPermissionDemand;
import cn.siriusbot.siriuspro.entity.pojo.apipermission.ApiPermissionDemandIdentify;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.List;

public class APIPermissionImpl implements ApiPermissionApi {
    /**
     * 创建频道Api接口权限，授权链接
     *
     * @param bot_id          传入机器人对象ID
     * @param guild_id     频道ID
     * @param channel_id   子频道ID
     * @param api_identify Api权限需求标识对象
     * @param desc         机器人申请对于的API接口权限后，可使用功能的描述
     * @return Api接口权限需求对象
     */
    @SneakyThrows
    @Override
    public Tuple<ApiPermissionDemand,String> createApiGrantLink(String bot_id, String guild_id, String channel_id, ApiPermissionDemandIdentify api_identify, String desc) {
        SiriusBotClient siriusBotClient = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl()+"guilds/"+guild_id+"/api_permission/demand").build();
        JSONObject json = new JSONObject();
        MediaType mediaType = MediaType.parse("application/json;text/plain");
        json.put("channel_id",channel_id);
        json.put("api_identify",api_identify);
        json.put("desc",desc);
        RequestBody body = RequestBody.create(mediaType,json.toJSONString());
        String data = SiriusHttpUtils.postRequest(siriusBotClient, request, body).body().string();
        Tuple<ApiPermissionDemand,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data,ApiPermissionDemand.class)).setSecond(data);
        return tuple;
    }

    /**
     * 获取频道可用权限列表
     *
     * @param bot_id      传入机器人对象ID
     * @param guild_id 频道ID
     * @return 返回可用Api权限对象列表
     */
    @SneakyThrows
    @Override
    public Tuple<List<APIPermission>,String> getAPIPermissions(String bot_id, String guild_id) {
        SiriusBotClient siriusBotClient = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl()+"guilds/"+guild_id+"/api_permission").build();
        String data = SiriusHttpUtils.getRequest(siriusBotClient,request).body().string();
        JSONObject json = JSONObject.parseObject(data);
        Tuple<List<APIPermission>,String> tuple = new Tuple<>();
        tuple.setFirst( json.getObject("apis",List.class)).setSecond(data);
        return tuple;
    }
}
