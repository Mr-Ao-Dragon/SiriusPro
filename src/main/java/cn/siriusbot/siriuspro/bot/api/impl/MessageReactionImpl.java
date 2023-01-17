package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.api.MessageReactionApi;
import cn.siriusbot.siriuspro.bot.api.pojo.emoji.ReactionReply;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.BotHttpEvent;
import cn.siriusbot.siriuspro.bot.pojo.BotRequest;
import cn.siriusbot.siriuspro.bot.pojo.BotResponse;
import cn.siriusbot.siriuspro.bot.pojo.e.RequestMethod;
import cn.siriusbot.siriuspro.config.bean.BotPool;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageReactionImpl implements MessageReactionApi {

    @Autowired
    BotPool botPool;

    /**
     * 拉取表情表态用户列表
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param type       表情类型
     * @param id         表情ID
     * @param cookie     分页参数
     * @param limit      每次拉取数量，默认20，最多50，只在第一次请求设置
     * @return 拉取表情表态响应对象
     */
    @SneakyThrows
    @Override
    public Tuple<ReactionReply, String> getReactionUsers(@NotNull String bot_id, String channel_id, String message_id, Integer type, String id, String cookie, Integer limit) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.GET);
        if (cookie == null) {
            botRequest = botRequest.setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/messages/" + message_id + "/reactions/" + type + "/" + id + "?limit=" + limit);
        } else {
            botRequest = botRequest.setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/messages/" + message_id + "/reactions/" + type + "/" + id + "?cookie=" + cookie + "&limit=" + limit);
        }
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = response.getBody();
        Tuple<ReactionReply, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, ReactionReply.class)).setSecond(data);
        return tuple;

    }

    /**
     * 删除机器人对指定消息的表态
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param type       表情类型，参考EmojiType
     * @param id         表情ID，参考Emoji列表
     * @return 操作结果
     */
    @SneakyThrows
    @Override
    public Boolean deleteReactionForMessageId(@NotNull String bot_id, String channel_id, String message_id, Integer type, String id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.DELETE)
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/messages/" + message_id + "/reactions/" + type + "/" + id);

        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 204;
    }

    /**
     * 发表表情表态
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param type       表情类型，参考EmojiType
     * @param id         表情ID，参考Emoji列表
     * @return 操作结果
     */
    @Override
    public Boolean addReaction(@NotNull String bot_id, String channel_id, String message_id, Integer type, String id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/messages/" + message_id + "/reactions/" + type + "/" + id)
                .setMethod(RequestMethod.PUT);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 204;
    }
}
