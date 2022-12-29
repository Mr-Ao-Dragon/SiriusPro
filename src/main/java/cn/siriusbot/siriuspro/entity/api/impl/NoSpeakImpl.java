package cn.siriusbot.siriuspro.entity.api.impl;

import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.NoSpeakApi;
import cn.siriusbot.siriuspro.entity.pojo.NoSpeak;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.List;

public class NoSpeakImpl implements NoSpeakApi {
    /**
     * 禁言指定成员
     * @param bot_id 传入机器人对象ID
     * @param guild_id 频道ID
     * @param user_id 用户ID
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds 禁言秒数
     * @return 返回禁言结果
     */
    @Override
    public Boolean noSpeakByUser_id(String bot_id, String guild_id, String user_id, String mute_end_timestamp, String mute_seconds) {
        SiriusBotClient siriusBotClient = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "guilds/" + guild_id + "/members/" + user_id + "/mute").build();
        JSONObject json = new JSONObject();
        json.put("mute_end_timestamp", mute_end_timestamp);
        json.put("mute_seconds", mute_seconds);
        MediaType mediaType = MediaType.parse("application/json;text/plain");
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.patchRequest(siriusBotClient, request, body);
        return response.code() == 204;
    }


    /**
     * 批量禁言成员
     * @param bot_id 传入机器人对象ID
     * @param guild_id 频道ID
     * @param user_ids 需要禁言的成员列表
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds 禁言秒数
     * @return 返回禁言成员对象
     */
    @SneakyThrows
    @Override
    public Tuple<NoSpeak,String> noSpeakByUser_ids(String bot_id, String guild_id, List<String> user_ids, String mute_end_timestamp, String mute_seconds) {
        SiriusBotClient siriusBotClient = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "guilds/" + guild_id + "/mute").build();
        JSONObject json = new JSONObject();
        MediaType mediaType = MediaType.parse("application/json;text/plain");
        json.put("mute_end_timestamp", mute_end_timestamp);
        json.put("mute_seconds", mute_seconds);
        json.put("user_ids", user_ids);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.patchRequest(siriusBotClient, request, body);
        String data = response.body().string();
        Tuple<NoSpeak,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, NoSpeak.class)).setSecond(data);
        return tuple;
    }


    /**
     * 全员禁言
     * @param bot_id 传入机器人对象ID
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds 禁言秒数
     * @return 返回禁言结果
     */
    @Override
    public Boolean nodeSpeakAll(String bot_id, String guild_id, String mute_end_timestamp, String mute_seconds) {
        SiriusBotClient siriusBotClient = BotManager.getBotByBotId(bot_id);
        MediaType mediaType = MediaType.parse("application/json;text/plain");
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "guilds/" + guild_id + "/mute").build();
        JSONObject json = new JSONObject();
        json.put("mute_end_timestamp", mute_end_timestamp);
        json.put("mute_seconds", mute_seconds);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.patchRequest(siriusBotClient, request, body);
        return response.code() == 204;
    }
}
