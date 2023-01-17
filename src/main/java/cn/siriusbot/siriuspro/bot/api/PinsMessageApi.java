package cn.siriusbot.siriuspro.bot.api;

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
     * @param bot_id 传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @return 返回精华消息对象
     */
    public abstract Tuple<PinsMessage,String> addPinsMessage(@NonNull @ENonNull String bot_id, String channel_id, String message_id);


    /**
     * 获取当前子频道精华消息
     * @param bot_id 传入机器人ID
     * @param channel_id 子频道ID
     * @return 返回精华消息对象
     */
    public abstract Tuple<PinsMessage,String> getPinsMessage(@NonNull @ENonNull String bot_id, String channel_id);

    /**
     * 删除精华消息
     * @param bot_id 传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @return 删除结果
     */
    public abstract Boolean deletePinsMessage(@NonNull @ENonNull String bot_id,String channel_id,String message_id);
}
