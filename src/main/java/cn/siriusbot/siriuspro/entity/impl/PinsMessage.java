package cn.siriusbot.siriuspro.entity.impl;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.PinsMessageApi;
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
public class PinsMessage implements PinsMessageApi {
    /**
     * 频道ID
     */
    private String guild_id;

    /**
     * 子频道ID
     */
    private String chanel_id;

    /**
     * 子频道内精华消息id数组
     */
    private List<String> message_ids;


    /**
     * 添加精华消息
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @return 返回精华消息对象
     */
    @SneakyThrows
    @Override
    public Map<PinsMessage, Object> addPinsMessage(Bot bot, String channel_id, String message_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/pins/" + message_id).build();
        MediaType mediaType = MediaType.parse("application/json;text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Response response = SiriusHttpUtils.putRequest(bot, request, body);
        String data = response.body().string();
        PinsMessage pinsMessage = JSONObject.parseObject(data, this.getClass());
        Map<PinsMessage, Object> map = new HashMap<>();
        map.put(pinsMessage, data);
        return map;
    }


    /**
     * 获取当前子频道精华消息
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @return 返回精华消息对象
     */
    @SneakyThrows
    @Override
    public Map<PinsMessage, Object> getPinsMessage(Bot bot, String channel_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/pins").build();
        String data = SiriusHttpUtils.getRequest(bot, request).body().string();
        PinsMessage pinsMessage = JSONObject.parseObject(SiriusHttpUtils.getRequest(bot, request).body().string(), this.getClass());
        Map<PinsMessage,Object> map = new HashMap<>();
        map.put(pinsMessage,data);
        return map;
    }


    /**
     * 删除精华消息
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @return 删除结果
     */
    @Override
    public Boolean deletePinsMessage(Bot bot, String channel_id, String message_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/pins/" + message_id).build();
        return SiriusHttpUtils.deleteRequest(bot, request, null).code() == 204;
    }
}
