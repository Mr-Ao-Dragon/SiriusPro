package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.api.PinsMessageApi;
import cn.siriusbot.siriuspro.bot.api.pojo.PinsMessage;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
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
@Component
public class  PinsMessageImpl implements PinsMessageApi {

    @Autowired
    BotManager botManager;
    
    /**
     * 添加精华消息
     *
     * @param bot_id        传入机器人对象ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @return 返回精华消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<PinsMessage, String> addPinsMessage(String bot_id, String channel_id, String message_id) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "channels/" + channel_id + "/pins/" + message_id).build();
        MediaType mediaType = MediaType.parse("application/json;text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Response response = SiriusHttpUtils.putRequest(siriusBotClient, request, body);
        String data = response.body().string();
        data = EmojiParser.parseToUnicode(data);
        PinsMessage pinsMessage = JSONObject.parseObject(data, PinsMessage.class);
        Tuple<PinsMessage, String> tuple = new Tuple<>();
        tuple.setFirst(pinsMessage).setSecond(data);
        return tuple;
    }


    /**
     * 获取当前子频道精华消息
     *
     * @param bot_id        传入机器人对象ID
     * @param channel_id 子频道ID
     * @return 返回精华消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<PinsMessage,String> getPinsMessage(String bot_id, String channel_id) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "channels/" + channel_id + "/pins").build();
        String data = SiriusHttpUtils.getRequest(siriusBotClient, request).body().string();
        data = EmojiParser.parseToUnicode(data);
        PinsMessage pinsMessage = JSONObject.parseObject(SiriusHttpUtils.getRequest(siriusBotClient, request).body().string(), PinsMessage.class);
        Tuple<PinsMessage,String> tuple = new Tuple<>();
        tuple.setFirst(pinsMessage).setSecond(data);
        return tuple;
    }


    /**
     * 删除精华消息
     *
     * @param bot_id        传入机器人对象ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @return 删除结果
     */
    @Override
    public Boolean deletePinsMessage(String bot_id, String channel_id, String message_id) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "channels/" + channel_id + "/pins/" + message_id).build();
        return SiriusHttpUtils.deleteRequest(siriusBotClient, request, null).code() == 204;
    }
}
