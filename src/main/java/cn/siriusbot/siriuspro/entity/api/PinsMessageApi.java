package cn.siriusbot.siriuspro.entity.api;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.entity.pojo.PinsMessage;
import cn.siriusbot.siriuspro.entity.temp.Tuple;

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
    public abstract Tuple<PinsMessage,String> addPinsMessage(String bot_id, String channel_id, String message_id);


    /**
     * 获取当前子频道精华消息
     * @param bot_id 传入机器人ID
     * @param channel_id 子频道ID
     * @return 返回精华消息对象
     */
    public abstract Tuple<PinsMessage,String> getPinsMessage(String bot_id, String channel_id);

    /**
     * 删除精华消息
     * @param bot_id 传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @return 删除结果
     */
    public abstract Boolean deletePinsMessage(String bot_id,String channel_id,String message_id);
}
