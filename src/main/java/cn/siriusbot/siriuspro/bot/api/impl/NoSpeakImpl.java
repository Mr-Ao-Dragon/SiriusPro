package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.api.NoSpeakApi;
import cn.siriusbot.siriuspro.bot.api.pojo.NoSpeak;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.BotHttpEvent;
import cn.siriusbot.siriuspro.bot.pojo.BotRequest;
import cn.siriusbot.siriuspro.bot.pojo.BotResponse;
import cn.siriusbot.siriuspro.bot.pojo.e.RequestMethod;
import cn.siriusbot.siriuspro.config.bean.BotPool;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoSpeakImpl implements NoSpeakApi {

    @Autowired
    BotManager botManager;

    @Autowired
    BotPool botPool;

    /**
     * 禁言指定成员
     *
     * @param bot_id             传入机器人对象ID
     * @param guild_id           频道ID
     * @param user_id            用户ID
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds       禁言秒数
     * @return 返回禁言结果
     */
    @Override
    public Boolean noSpeakByUser_id(String bot_id, String guild_id, String user_id, String mute_end_timestamp, String mute_seconds) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/members/" + user_id + "/mute")
                .setMethod(RequestMethod.PATCH)
                .putRequestBody("mute_end_timestamp", mute_end_timestamp)
                .putRequestBody("mute_seconds", mute_seconds);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 204;
    }


    /**
     * 批量禁言成员
     *
     * @param bot_id             传入机器人对象ID
     * @param guild_id           频道ID
     * @param user_ids           需要禁言的成员列表
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds       禁言秒数
     * @return 返回禁言成员对象
     */
    @SneakyThrows
    @Override
    public Tuple<NoSpeak, String> noSpeakByUser_ids(String bot_id, String guild_id, List<String> user_ids, String mute_end_timestamp, String mute_seconds) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.PATCH)
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/mute")
                .putRequestBody("mute_end_timestamp", mute_end_timestamp)
                .putRequestBody("mute_seconds", mute_seconds)
                .putRequestBody("user_ids", user_ids);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = response.getBody();
        Tuple<NoSpeak, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, NoSpeak.class)).setSecond(data);
        return tuple;
    }


    /**
     * 全员禁言
     *
     * @param bot_id             传入机器人对象ID
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds       禁言秒数
     * @return 返回禁言结果
     */
    @Override
    public Boolean nodeSpeakAll(String bot_id, String guild_id, String mute_end_timestamp, String mute_seconds) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/mute")
                .setMethod(RequestMethod.PATCH)
                .putRequestBody("mute_end_timestamp", mute_end_timestamp)
                .putRequestBody("mute_seconds", mute_seconds);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 204;
    }
}
