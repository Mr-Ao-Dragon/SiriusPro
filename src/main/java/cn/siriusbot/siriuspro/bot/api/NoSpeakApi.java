package cn.siriusbot.siriuspro.bot.api;

import cn.siriusbot.siriuspro.bot.annotation.EDoc;
import cn.siriusbot.siriuspro.bot.annotation.EName;
import cn.siriusbot.siriuspro.bot.annotation.ENonNull;
import cn.siriusbot.siriuspro.bot.api.pojo.NoSpeak;
import cn.siriusbot.siriuspro.bot.api.proxy.ApiProxy;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import lombok.NonNull;

import java.util.List;

/**
 * 禁言Api
 */
public interface NoSpeakApi extends ApiProxy {

    /**
     * 禁言指定成员
     *
     * @param bot_id             传入机器人ID
     * @param guild_id           频道ID
     * @param user_id            用户ID
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds       禁言秒数
     * @return 返回禁言结果
     */
    @EName(name = "禁言指定用户")
    Boolean noSpeakByUser_id
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull String guild_id,

            @EDoc(doc = "用户ID")
            @NonNull @ENonNull String user_id,

            @EDoc(doc = "禁言到期时间戳")
            String mute_end_timestamp,

            @EDoc(doc = "禁言描述")
            String mute_seconds
    );


    /**
     * 批量禁言成员
     *
     * @param bot_id             传入机器人ID
     * @param guild_id           频道ID
     * @param user_ids           需要禁言的成员列表
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds       禁言秒数
     * @return 返回禁言成员对象
     */
    @EName(name = "批量禁言用户")
    Tuple<NoSpeak, String> noSpeakByUser_ids
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull String guild_id,

            @EDoc(doc = "用户ID数组")
            @NonNull @ENonNull List<String> user_ids,

            @EDoc(doc = "禁言到期时间戳，绝对时间戳，单位：秒（与 mute_seconds 字段同时赋值的话，以该字段为准）")
            String mute_end_timestamp,

            @EDoc(doc = "禁言多少秒（两个字段二选一，默认以 mute_end_timestamp 为准）")
            String mute_seconds
    );


    /**
     * 全员禁言
     *
     * @param bot_id             传入机器人ID
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds       禁言秒数
     * @return 返回禁言结果
     */
    @EName(name = "全员禁言")
    Boolean nodeSpeakAll
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull
            String guild_id,

            @EDoc(doc = "禁言到期时间戳")
            String mute_end_timestamp,

            @EDoc(doc = "禁言秒数")
            String mute_seconds);
}
