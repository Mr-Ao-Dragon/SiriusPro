package cn.siriusbot.siriuspro.entity.impl;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.NoSpeakApi;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@Accessors(chain = true)
public class NoSpeak implements NoSpeakApi {

    /**
     * 禁言到期时间戳
     */
    private String mute_end_timestamp;

    /**
     * 禁言秒数
     */
    private String seconds;

    /**
     * 禁言人员列表
     */
    private List<String> user_ids;


    /**
     * 禁言指定成员
     * @param bot 传入机器人对象
     * @param guild_id 频道ID
     * @param user_id 用户ID
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds 禁言秒数
     * @return 返回禁言结果
     */
    @Override
    public Boolean noSpeakByUser_id(Bot bot, String guild_id, String user_id, String mute_end_timestamp, String mute_seconds) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/members/" + user_id + "/mute").build();
        JSONObject json = new JSONObject();
        json.put("mute_end_timestamp", mute_end_timestamp);
        json.put("mute_seconds", mute_seconds);
        MediaType mediaType = MediaType.parse("application/json;text/plain");
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.patchRequest(bot, request, body);
        return response.code() == 204;
    }


    /**
     * 批量禁言成员
     * @param bot 传入机器人对象
     * @param guild_id 频道ID
     * @param user_ids 需要禁言的成员列表
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds 禁言秒数
     * @return 返回禁言成员对象
     */
    @SneakyThrows
    @Override
    public Map<NoSpeak,Object> noSpeakByUser_ids(Bot bot, String guild_id, List<String> user_ids, String mute_end_timestamp, String mute_seconds) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/mute").build();
        JSONObject json = new JSONObject();
        MediaType mediaType = MediaType.parse("application/json;text/plain");
        json.put("mute_end_timestamp", mute_end_timestamp);
        json.put("mute_seconds", mute_seconds);
        json.put("user_ids", user_ids);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.patchRequest(bot, request, body);
        String data = response.body().string();
        Map<NoSpeak,Object> map = new HashMap<>();
        map.put(JSONObject.parseObject(data, this.getClass()),data);
        return map;
    }


    /**
     * 全员禁言
     * @param bot 传入机器人对象
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds 禁言秒数
     * @return 返回禁言结果
     */
    @Override
    public Boolean nodeSpeakAll(Bot bot, String guild_id, String mute_end_timestamp, String mute_seconds) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        MediaType mediaType = MediaType.parse("application/json;text/plain");
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/mute").build();
        JSONObject json = new JSONObject();
        json.put("mute_end_timestamp", mute_end_timestamp);
        json.put("mute_seconds", mute_seconds);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.patchRequest(bot, request, body);
        return response.code() == 204;
    }
}
