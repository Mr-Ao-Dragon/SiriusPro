package cn.siriusbot.siriuspro.bot.api;


import cn.siriusbot.siriuspro.bot.annotation.ENonNull;
import cn.siriusbot.siriuspro.bot.api.pojo.emoji.ReactionReply;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import lombok.NonNull;

/**
 * 消息表情表态Api
 */
public interface MessageReactionApi {

    /**
     * 拉取表情表态用户列表
     *
     * @param bot_id        传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param type       表情类型
     * @param id         表情ID
     * @param cookie     分页参数
     * @param limit      每次拉取数量，默认20，最多50，只在第一次请求设置
     * @return 拉取表情表态响应对象
     */
    public Tuple<ReactionReply,String> getReactionUsers(@NonNull @ENonNull String bot_id, String channel_id, String message_id, Integer type, String id, String cookie, Integer limit);


    /**
     * 删除机器人对指定消息的表态
     *
     * @param bot_id        传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param type       表情类型，参考EmojiType
     * @param id         表情ID，参考Emoji列表
     * @return 操作结果
     */
    public abstract Boolean deleteReactionForMessageId(@NonNull @ENonNull String bot_id, String channel_id, String message_id, Integer type, String id);

    /**
     * 发表表情表态
     *
     * @param bot_id        传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param type       表情类型，参考EmojiType
     * @param id         表情ID，参考Emoji列表
     * @return 操作结果
     */
    public abstract Boolean addReaction(@NonNull @ENonNull String bot_id, String channel_id, String message_id, Integer type, String id);
}
