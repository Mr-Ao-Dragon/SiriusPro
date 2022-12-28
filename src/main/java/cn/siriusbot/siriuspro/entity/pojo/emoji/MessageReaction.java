package cn.siriusbot.siriuspro.entity.pojo.emoji;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.MessageReactionApi;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 表情表态对象
 */
@Data
@Accessors(chain = true)
public class MessageReaction implements MessageReactionApi {

    /**
     * 用户ID
     */
    private String user_id;

    /**
     * 频道ID
     */
    private String guild_id;

    /**
     * 子频道ID
     */
    private String channel_id;

    /**
     * 表态对象
     */
    private ReactionTarget target;

    /**
     * 表态所用表情
     */
    private Emoji emoji;

    /**
     * 拉取表情表态用户列表
     *
     * @param bot        传入机器人对象
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
    public Tuple<ReactionReply,String> getReactionUsers(Bot bot, String channel_id, String message_id, Integer type, String id, String cookie, Integer limit) {
        bot = BotManager.getBotByBotId(bot.getBotId());
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
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param type       表情类型，参考EmojiType
     * @param id         表情ID，参考Emoji列表
     * @return 操作结果
     */
    @SneakyThrows
    @Override
    public Boolean deleteReactionForMessageId(Bot bot, String channel_id, String message_id, Integer type, String id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/messages/" + message_id + "/reactions/" + type + "/" + id).build();
        return SiriusHttpUtils.deleteRequest(bot, request,null).code() == 204;
    }

    /**
     * 发表表情表态
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param type       表情类型，参考EmojiType
     * @param id         表情ID，参考Emoji列表
     * @return 操作结果
     */
    @Override
    public Boolean addReaction(Bot bot, String channel_id, String message_id, Integer type, String id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/messages/" + message_id + "/reactions/" + type + "/" + id).build();
        return SiriusHttpUtils.putRequest(bot, request, RequestBody.create(MediaType.parse("application/json;text/plain"), "")).code() == 204;
    }
}
