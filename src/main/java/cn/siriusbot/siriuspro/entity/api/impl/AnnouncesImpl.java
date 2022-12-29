package cn.siriusbot.siriuspro.entity.api.impl;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.AnnouncesApi;
import cn.siriusbot.siriuspro.entity.pojo.announces.Announces;
import cn.siriusbot.siriuspro.entity.pojo.announces.RecommendChannel;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.List;

public class AnnouncesImpl implements AnnouncesApi {
    /**
     * 创建频道公告
     * 用于创建频道全局公告，公告类型分为 消息类型的频道公告 和 推荐子频道类型的频道公告 。
     * 当请求参数 message_id 有值时，优先创建消息类型的频道公告， 消息类型的频道公告只能创建成员公告类型的频道公告。
     * 创建推荐子频道类型的频道全局公告请将 message_id 设置为空，并设置对应的 announces_type 和 recommend_channels 请求参数，会一次全部替换推荐子频道公司。
     * 推荐子频道和消息类型全局公告不能同时存在，会互相顶替设置。
     * 同频道内推荐子频道最多只能创建 3 条。
     * 只有子频道权限为全体成员可见才可设置为推荐子频道。
     * 删除推荐子频道类型的频道公告请使用 删除频道公告,并将 message_id 设置为 all。
     *
     * @param bot_id               传入机器人对象ID
     * @param guild_id          频道ID
     * @param message_id        消息ID
     * @param channel_id        子频道ID
     * @return 返回公告对象
     */
    @SneakyThrows
    @Override
    public Tuple<Announces,String> createGuildAnnounces(String bot_id, String guild_id, String message_id, String channel_id) {
        Bot bot = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/announces").build();
        MediaType mediaType = MediaType.parse("application/json;text/plain");
        JSONObject json = new JSONObject();
        json.put("message_id", message_id);
        json.put("channel_id", channel_id);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(bot, request, body);
        String data = response.body().string();
        Tuple<Announces,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Announces.class)).setSecond(data);
        return tuple;
    }


    /**
     * 删除频道公告
     * 用于删除频道 guild_id 下指定 message_id 的全局公告。
     * message_id 有值时，会校验 message_id 合法性，若不校验校验 message_id，请将 message_id 设置为 all
     *
     * @param bot_id        传入机器人对象ID
     * @param guild_id   频道ID
     * @param message_id 消息ID
     * @return 返回删除结果
     */
    @Override
    public Boolean deleteAnnouncesByGuildId(String bot_id, String guild_id, String message_id) {
        Bot bot = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/announces/" + message_id).build();
        Response response = SiriusHttpUtils.deleteRequest(bot, request, null);
        return response.code() == 204;
    }

    /**
     * 创建频道推荐子频道列表
     * @param bot_id 传入机器人对象ID
     * @param guild_id 频道ID
     * @param recommendChannels 机器人推荐列表
     * @return 返回公告对象
     */
    @SneakyThrows
    @Override
    public Tuple<Announces,String> createGuildRecommend_Channels(String bot_id, String guild_id, Integer announces_type, List<RecommendChannel> recommendChannels) {
        Bot bot = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(bot.getOpenUrl()+"guilds/"+guild_id+"/announces").build();
        MediaType mediaType = MediaType.parse("application/json;text/plain");
        JSONObject json = new JSONObject();
        json.put("recommend_channels",recommendChannels);
        json.put("announces_type",announces_type);
        RequestBody body = RequestBody.create(mediaType,json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(bot, request, body);
        String data = response.body().string();
        Tuple<Announces,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Announces.class)).setSecond(data);
        return tuple;
    }
}
