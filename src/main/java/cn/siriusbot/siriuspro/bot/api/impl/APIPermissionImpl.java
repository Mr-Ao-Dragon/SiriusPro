package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.api.ApiPermissionApi;
import cn.siriusbot.siriuspro.bot.api.pojo.apipermission.APIPermission;
import cn.siriusbot.siriuspro.bot.api.pojo.apipermission.ApiPermissionDemand;
import cn.siriusbot.siriuspro.bot.api.pojo.apipermission.ApiPermissionDemandIdentify;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.BotHttpEvent;
import cn.siriusbot.siriuspro.bot.pojo.BotRequest;
import cn.siriusbot.siriuspro.bot.pojo.BotResponse;
import cn.siriusbot.siriuspro.bot.pojo.e.RequestMethod;
import cn.siriusbot.siriuspro.config.bean.BotPool;
import com.alibaba.fastjson.JSONObject;
import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class APIPermissionImpl implements ApiPermissionApi {

    @Autowired
    BotPool botPool;


    /**
     * 创建频道Api接口权限，授权链接
     *
     * @param bot_id       传入机器人对象ID
     * @param guild_id     频道ID
     * @param channel_id   子频道ID
     * @param api_identify Api权限需求标识对象
     * @param desc         机器人申请对于的API接口权限后，可使用功能的描述
     * @return Api接口权限需求对象
     */
    @SneakyThrows
    @Override
    public Tuple<ApiPermissionDemand, String> createApiGrantLink(@NotNull String bot_id, String guild_id, String channel_id, ApiPermissionDemandIdentify api_identify, String desc) {
        BotClient client = botPool.getBotById(bot_id);
        desc = EmojiParser.parseToUnicode(desc);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.POST)
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/api_permission/demand")
                .putRequestBody("channel_id", channel_id)
                .putRequestBody("api_identify", api_identify)
                .putRequestBody("desc", desc);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        Tuple<ApiPermissionDemand, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, ApiPermissionDemand.class)).setSecond(data);
        return tuple;
    }

    /**
     * 获取频道可用权限列表
     *
     * @param bot_id   传入机器人对象ID
     * @param guild_id 频道ID
     * @return 返回可用Api权限对象列表
     */
    @SneakyThrows
    @Override
    public Tuple<List<APIPermission>, String> getAPIPermissions(@NotNull String bot_id, String guild_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.GET)
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/api_permission");
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = response.getBody();
        JSONObject json = JSONObject.parseObject(data);
        Tuple<List<APIPermission>, String> tuple = new Tuple<>();
        tuple.setFirst(json.getObject("apis", List.class)).setSecond(data);
        return tuple;
    }
}
