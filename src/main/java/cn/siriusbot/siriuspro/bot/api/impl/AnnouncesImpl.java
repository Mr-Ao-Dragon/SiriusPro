package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.api.AnnouncesApi;
import cn.siriusbot.siriuspro.bot.api.pojo.announces.Announces;
import cn.siriusbot.siriuspro.bot.api.pojo.announces.RecommendChannel;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;

import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.BotHttpEvent;
import cn.siriusbot.siriuspro.bot.pojo.BotRequest;
import cn.siriusbot.siriuspro.bot.pojo.BotResponse;
import cn.siriusbot.siriuspro.bot.pojo.e.RequestMethod;
import cn.siriusbot.siriuspro.config.bean.BotPool;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnnouncesImpl implements AnnouncesApi {

    @Autowired
    BotPool botPool;

    @Autowired
    BotManager botManager;

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
     * @param bot_id     传入机器人对象ID
     * @param guild_id   频道ID
     * @param message_id 消息ID
     * @param channel_id 子频道ID
     * @return 返回公告对象
     */
    @Override
    public Tuple<Announces, String> createGuildAnnounces(String bot_id, String guild_id, String message_id, String channel_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.POST)
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/announces")
                .setMediaType("application/json;text/plain")
                .putRequestBody("message_id", message_id)
                .putRequestBody("channel_id", channel_id);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        Tuple<Announces, String> tuple = new Tuple<>();
        tuple
                .setFirst(JSONObject.parseObject(response.getBody(), Announces.class))
                .setSecond(response.getBody());
        return tuple;
    }


    /**
     * 删除频道公告
     * 用于删除频道 guild_id 下指定 message_id 的全局公告。
     * message_id 有值时，会校验 message_id 合法性，若不校验校验 message_id，请将 message_id 设置为 all
     *
     * @param bot_id     传入机器人对象ID
     * @param guild_id   频道ID
     * @param message_id 消息ID
     * @return 返回删除结果
     */
    @SneakyThrows
    @Override
    public Boolean deleteAnnouncesByGuildId(String bot_id, String guild_id, String message_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.DELETE)
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/announces/" + message_id);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 204;
    }

    /**
     * 创建频道推荐子频道列表
     *
     * @param bot_id            传入机器人对象ID
     * @param guild_id          频道ID
     * @param recommendChannels 机器人推荐列表
     * @return 返回公告对象
     */
    @SneakyThrows
    @Override
    public Tuple<Announces, String> createGuildRecommend_Channels(String bot_id, String guild_id, Integer announces_type, List<RecommendChannel> recommendChannels) {
        BotClient client = botPool.getBotById(bot_id);
        for (int i = 0; i < recommendChannels.size(); i++) {
            recommendChannels.get(i).setIntroduce(EmojiParser.parseToUnicode(recommendChannels.get(i).getIntroduce()));
        }
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/announces")
                .setMethod(RequestMethod.POST)
                .putRequestBody("recommend_channels", recommendChannels)
                .putRequestBody("announces_type", announces_type);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        Tuple<Announces, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(response.getBody(), Announces.class)).setSecond(response.getBody());
        return tuple;
    }
}
