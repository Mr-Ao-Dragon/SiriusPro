package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.api.PinsMessageApi;
import cn.siriusbot.siriuspro.bot.api.pojo.PinsMessage;
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

@Component
public class PinsMessageImpl implements PinsMessageApi {


    @Autowired
    BotPool botPool;

    /**
     * 添加精华消息
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @return 返回精华消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<PinsMessage, String> addPinsMessage(@NotNull String bot_id, @NotNull String channel_id, @NotNull String message_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() +"channels/" + channel_id + "/pins/" + message_id)
                .setMethod(RequestMethod.PUT);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        PinsMessage pinsMessage = JSONObject.parseObject(data, PinsMessage.class);
        Tuple<PinsMessage, String> tuple = new Tuple<>();
        tuple.setFirst(pinsMessage).setSecond(data);
        return tuple;
    }


    /**
     * 获取当前子频道精华消息
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @return 返回精华消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<PinsMessage, String> getPinsMessage(@NotNull String bot_id, @NotNull String channel_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.GET)
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/pins");
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        PinsMessage pinsMessage = JSONObject.parseObject(data, PinsMessage.class);
        Tuple<PinsMessage, String> tuple = new Tuple<>();
        tuple.setFirst(pinsMessage).setSecond(data);
        return tuple;
    }


    /**
     * 删除精华消息
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @return 删除结果
     */
    @Override
    public Boolean deletePinsMessage(@NotNull String bot_id, @NotNull String channel_id, @NotNull String message_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/pins/" + message_id)
                .setMethod(RequestMethod.DELETE);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 204;
    }
}
