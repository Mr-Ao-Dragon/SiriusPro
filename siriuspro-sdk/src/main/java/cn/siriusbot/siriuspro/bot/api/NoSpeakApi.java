package cn.siriusbot.siriuspro.bot.api;

import cn.siriusbot.siriuspro.bot.api.pojo.NoSpeak;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;

import java.util.List;

/**
 * 禁言Api
 */
public interface NoSpeakApi {

    /**
     * 禁言指定成员
     * @param bot_id 传入机器人ID
     * @param guild_id 频道ID
     * @param user_id 用户ID
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds 禁言秒数
     * @return 返回禁言结果
     */
    public abstract Boolean noSpeakByUser_id(String bot_id, String guild_id, String user_id, String mute_end_timestamp, String mute_seconds);


    /**
     * 批量禁言成员
     * @param bot_id 传入机器人ID
     * @param guild_id 频道ID
     * @param user_ids 需要禁言的成员列表
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds 禁言秒数
     * @return 返回禁言成员对象
     */
    public abstract Tuple<NoSpeak,String> noSpeakByUser_ids(String bot_id, String guild_id, List<String> user_ids, String mute_end_timestamp, String mute_seconds);


    /**
     * 全员禁言
     * @param bot_id 传入机器人ID
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds 禁言秒数
     * @return 返回禁言结果
     */
    public abstract Boolean nodeSpeakAll(String bot_id,String guild_id,String mute_end_timestamp,String mute_seconds);
}
