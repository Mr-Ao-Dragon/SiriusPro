package cn.siriusbot.siriuspro.entity.api.impl;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.ChannelApi;
import cn.siriusbot.siriuspro.entity.pojo.Channel;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.List;

public class ChannelImpl implements ChannelApi {
    /**
     * 获取子频道列表
     * @param bot 传入机器人对象
     * @param guild_id 频道ID
     * @return 返回存放子频道的容器对象
     */
    @SneakyThrows
    @Override
    public Tuple<List<Channel>,String> getChannelList(Bot bot, String guild_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/channels").build();
        Response response = SiriusHttpUtils.getRequest(bot, request);
        String data = response.body().string();

        Tuple<List<Channel>,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, List.class)).setSecond(data);
        return tuple;
    }


    /**
     * 获取子频道详情
     * @param bot 传入机器人对象
     * @param channel_id 子频道ID
     * @return 返回子频道对象
     */
    @SneakyThrows
    @Override
    public Tuple<Channel,String> getChannelInfo(Bot bot, String channel_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id).build();
        Response response = SiriusHttpUtils.getRequest(bot, request);
        String data = response.body().string();
        Tuple<Channel,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Channel.class)).setSecond(data);
        return tuple;
    }

    /**
     * 创建子频道
     * @param bot 传入机器人对象
     * @param guild_id 频道ID
     * @param channel 子频道对象
     * @return 返回子频道对象
     */
    @SneakyThrows
    @Override
    public Tuple<Channel,String>createChannel(Bot bot, String guild_id, Channel channel) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/channels").build();
        RequestBody body = RequestBody.create(mediaType, JSONObject.toJSONString(channel));
        Response response = SiriusHttpUtils.postRequest(bot, request, body);
        String data = response.body().string();
        Tuple<Channel,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Channel.class)).setSecond(data);
        return tuple;
    }

    /**
     * 修改子频道
     * @param bot 传入机器人对象
     * @param channel_id 子频道id
     * @param channel 修改后的子频道对象
     * @return 修改后的子频道对象
     */
    @SneakyThrows
    @Override
    public Tuple<Channel,String> modifyChannel(Bot bot, String channel_id, Channel channel) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id).build();
        RequestBody body = RequestBody.create(mediaType, JSONObject.toJSONString(channel));
        Response response = SiriusHttpUtils.patchRequest(bot, request, body);
        String data = response.body().string();
        Tuple<Channel,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Channel.class)).setSecond(data);
        return tuple;
    }

    /**
     * 删除子频道
     * @param bot 传入机器人对象
     * @param channel_id 子频道ID
     * @return 删除结果
     */
    @Override
    public Boolean deleteChannel(Bot bot, String channel_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id).build();
        Response response = SiriusHttpUtils.deleteRequest(bot, request, null);
        return response.code() == 200;
    }

    /**
     * 获取当前音视频/直播子频道的在线成员数
     * @param bot 传入机器人对象
     * @param channel_id 子频道ID
     * @return 在线人数
     */
    @SneakyThrows
    @Override
    public Integer getOnlineMemberNumber(Bot bot, String channel_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl()+"channels/"+channel_id+"/online_nums").build();
        Response response = SiriusHttpUtils.getRequest(bot, request);
        JSONObject json = JSONObject.parseObject(response.body().string());
        return json.getInteger("online_nums");
    }
}
