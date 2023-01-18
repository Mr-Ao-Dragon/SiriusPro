package cn.siriusbot.siriuspro.bot.api;

import cn.siriusbot.siriuspro.bot.annotation.EDoc;
import cn.siriusbot.siriuspro.bot.annotation.ENonNull;
import cn.siriusbot.siriuspro.bot.api.pojo.PinsMessage;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import lombok.NonNull;

/**
 * 精华消息对象Api
 */
public interface PinsMessageApi {

    /**
     * 添加精华消息
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @return 返回精华消息对象
     */
    @EDoc(doc = "添加精华消息")
    Tuple<PinsMessage, String> addPinsMessage
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull
            String channel_id,

            @EDoc(doc = "消息ID")
            @NonNull @ENonNull String message_id
    );


    /**
     * 获取当前子频道精华消息
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @return 返回精华消息对象
     */
    @EDoc(doc = "获取精华消息列表")
    Tuple<PinsMessage, String> getPinsMessage
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id
    );

    /**
     * 删除精华消息
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @return 删除结果
     */
    @EDoc(doc = "删除精华消息")
    Boolean deletePinsMessage
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "消息ID")
            @NonNull @ENonNull String message_id
    );
}
