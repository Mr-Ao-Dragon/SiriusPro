package cn.siriusbot.siriuspro.bot.api;


import cn.siriusbot.siriuspro.bot.annotation.EDoc;
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
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param type       表情类型
     * @param id         表情ID
     * @param cookie     分页参数
     * @param limit      每次拉取数量，默认20，最多50，只在第一次请求设置
     * @return 拉取表情表态响应对象
     */
    @EDoc(doc = "拉取表情表态用户列表")
    Tuple<ReactionReply, String> getReactionUsers
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "消息ID")
            @NonNull @ENonNull String message_id,

            @EDoc(doc = "表情类型")
            @NonNull @ENonNull Integer type,

            @EDoc(doc = "表情ID")
            @NonNull @ENonNull String id,

            @EDoc(doc = "分页参数")
            String cookie,

            @EDoc(doc = "拉取数量默认20，最多50")
            Integer limit
    );


    /**
     * 删除机器人对指定消息的表态
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param type       表情类型，参考EmojiType
     * @param id         表情ID，参考Emoji列表
     * @return 操作结果
     */
    @EDoc(doc = "删除表情表态")
    Boolean deleteReactionForMessageId
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "消息ID")
            @NonNull @ENonNull String message_id,

            @EDoc(doc = "表情类型")
            @NonNull @ENonNull Integer type,

            @EDoc(doc = "表情ID")
            @NonNull @ENonNull String id
    );

    /**
     * 发表表情表态
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param type       表情类型，参考EmojiType
     * @param id         表情ID，参考Emoji列表
     * @return 操作结果
     */
    @EDoc(doc = "发表表情表态")
    Boolean addReaction
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "消息ID")
            @NonNull @ENonNull String message_id,

            @EDoc(doc = "表情类型")
            @NonNull @ENonNull Integer type,

            @EDoc(doc = "表情ID")
            @NonNull @ENonNull String id
    );
}
