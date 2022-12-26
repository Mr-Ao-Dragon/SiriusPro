package cn.siriusbot.siriuspro.entity.api;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.entity.impl.NoSpeak;
import cn.siriusbot.siriuspro.entity.temp.Tuple;


import java.util.List;
import java.util.Map;

/**
 * 禁言Api
 */
public interface NoSpeakApi {

    /**
     * 禁言指定成员
     * @param bot 传入机器人对象
     * @param guild_id 频道ID
     * @param user_id 用户ID
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds 禁言秒数
     * @return 返回禁言结果
     */
    public abstract Boolean noSpeakByUser_id(Bot bot, String guild_id, String user_id, String mute_end_timestamp, String mute_seconds);


    /**
     * 批量禁言成员
     * @param bot 传入机器人对象
     * @param guild_id 频道ID
     * @param user_ids 需要禁言的成员列表
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds 禁言秒数
     * @return 返回禁言成员对象
     */
    public abstract Tuple<NoSpeak,String> noSpeakByUser_ids(Bot bot, String guild_id, List<String> user_ids, String mute_end_timestamp, String mute_seconds);


    /**
     * 全员禁言
     * @param bot 传入机器人对象
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds 禁言秒数
     * @return 返回禁言结果
     */
    public abstract Boolean nodeSpeakAll(Bot bot,String guild_id,String mute_end_timestamp,String mute_seconds);
}
