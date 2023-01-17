package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.api.ChannelApi;
import cn.siriusbot.siriuspro.bot.api.pojo.Channel;
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
public class ChannelImpl implements ChannelApi {

    @Autowired
    BotPool botPool;

    /**
     * 获取子频道列表
     *
     * @param bot_id   传入机器人对象ID
     * @param guild_id 频道ID
     * @return 返回存放子频道的容器对象
     */
    @SneakyThrows
    @Override
    public Tuple<List<Channel>, String> getChannelList(@NotNull String bot_id, String guild_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/channels")
                .setMethod(RequestMethod.GET);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);

        String data = response.getBody();
        data = EmojiParser.parseToUnicode(data);
        Tuple<List<Channel>, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, List.class)).setSecond(data);
        return tuple;
    }


    /**
     * 获取子频道详情
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @return 返回子频道对象
     */
    @SneakyThrows
    @Override
    public Tuple<Channel, String> getChannelInfo(@NotNull String bot_id, String channel_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.GET)
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = response.getBody();
        data = EmojiParser.parseToUnicode(data);
        Tuple<Channel, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Channel.class)).setSecond(data);
        return tuple;
    }

    /**
     * 创建子频道
     *
     * @param bot_id   传入机器人对象ID
     * @param guild_id 频道ID
     * @param channel  子频道对象
     * @return 返回子频道对象
     */
    @SneakyThrows
    @Override
    public Tuple<Channel, String> createChannel(@NotNull String bot_id, String guild_id, Channel channel) {
        BotClient client = botPool.getBotById(bot_id);
        channel.setName(EmojiParser.parseToUnicode(channel.getName()));
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/channels")
                .setMethod(RequestMethod.POST)
                .putRequestBody("name", channel.getName())
                .putRequestBody("type", channel.getType())
                .putRequestBody("sub_type", channel.getSub_type())
                .putRequestBody("position", channel.getPosition())
                .putRequestBody("parent_id", channel.getParent_id())
                .putRequestBody("private_type", channel.getPrivate_type())
                .putRequestBody("private_user_ids", channel.getPrivate_user_ids())
                .putRequestBody("speak_permission", channel.getSpeak_permission())
                .putRequestBody("application_id", channel.getApplication_id());
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = response.getBody();
        data = EmojiParser.parseToUnicode(data);
        Tuple<Channel, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Channel.class)).setSecond(data);
        return tuple;
    }

    /**
     * 修改子频道
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道id
     * @param channel    修改后的子频道对象
     * @return 修改后的子频道对象
     */
    @SneakyThrows
    @Override
    public Tuple<Channel, String> modifyChannel(@NotNull String bot_id, String channel_id, Channel channel) {
        BotClient client = botPool.getBotById(bot_id);
        channel.setName(EmojiParser.parseToUnicode(channel.getName()));
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id)
                .setMethod(RequestMethod.PATCH)
                .putRequestBody("name", channel.getName())
                .putRequestBody("type", channel.getType())
                .putRequestBody("sub_type", channel.getSub_type())
                .putRequestBody("position", channel.getPosition())
                .putRequestBody("parent_id", channel.getParent_id())
                .putRequestBody("private_type", channel.getPrivate_type())
                .putRequestBody("private_user_ids", channel.getPrivate_user_ids())
                .putRequestBody("speak_permission", channel.getSpeak_permission())
                .putRequestBody("application_id", channel.getApplication_id());
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        Tuple<Channel, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Channel.class)).setSecond(data);
        return tuple;
    }

    /**
     * 删除子频道
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @return 删除结果
     */
    @Override
    public Boolean deleteChannel(@NotNull String bot_id, String channel_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.DELETE)
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 200;
    }

    /**
     * 获取当前音视频/直播子频道的在线成员数
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @return 在线人数
     */
    @SneakyThrows
    @Override
    public Integer getOnlineMemberNumber(@NotNull String bot_id, String channel_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/online_nums")
                .setMethod(RequestMethod.GET);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        JSONObject json = JSONObject.parseObject(response.getBody());
        return json.getInteger("online_nums");
    }
}
