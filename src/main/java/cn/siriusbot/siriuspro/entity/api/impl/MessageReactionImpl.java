package cn.siriusbot.siriuspro.entity.api.impl;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.MessageReactionApi;
import cn.siriusbot.siriuspro.entity.pojo.emoji.ReactionReply;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MessageReactionImpl implements MessageReactionApi {
    /**
     * 拉取表情表态用户列表
     *
     * @param bot_id        传入机器人对象ID
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
    public Tuple<ReactionReply,String> getReactionUsers(String bot_id, String channel_id, String message_id, Integer type, String id, String cookie, Integer limit) {
        Bot bot = BotManager.getBotByBotId(bot_id);
        Request request;
        if(cookie==null){
            request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/messages/" + message_id + "/reactions/" + type + "/" + id + "?limit=" + limit).build();
        }else{
            request   = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/messages/" + message_id + "/reactions/" + type + "/" + id + "?cookie=" + cookie + "&limit=" + limit).build();
        }
        String data = SiriusHttpUtils.getRequest(bot, request).body().string();

        Tuple<ReactionReply,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data,ReactionReply.class)).setSecond(data);
        return tuple;

    }

    /**
     * 删除机器人对指定消息的表态
     *
     * @param bot_id        传入机器人对象ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param type       表情类型，参考EmojiType
     * @param id         表情ID，参考Emoji列表
     * @return 操作结果
     */
    @SneakyThrows
    @Override
    public Boolean deleteReactionForMessageId(String bot_id, String channel_id, String message_id, Integer type, String id) {
        Bot bot = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/messages/" + message_id + "/reactions/" + type + "/" + id).build();
        return SiriusHttpUtils.deleteRequest(bot, request,null).code() == 204;
    }

    /**
     * 发表表情表态
     *
     * @param bot_id        传入机器人对象ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param type       表情类型，参考EmojiType
     * @param id         表情ID，参考Emoji列表
     * @return 操作结果
     */
    @Override
    public Boolean addReaction(String bot_id, String channel_id, String message_id, Integer type, String id) {
        Bot bot = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/messages/" + message_id + "/reactions/" + type + "/" + id).build();
        return SiriusHttpUtils.putRequest(bot, request, RequestBody.create(MediaType.parse("application/json;text/plain"), "")).code() == 204;
    }
}
