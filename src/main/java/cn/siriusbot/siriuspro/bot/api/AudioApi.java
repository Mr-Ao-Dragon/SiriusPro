package cn.siriusbot.siriuspro.bot.api;

import cn.siriusbot.siriuspro.bot.annotation.EDoc;
import cn.siriusbot.siriuspro.bot.annotation.ENonNull;
import cn.siriusbot.siriuspro.bot.api.pojo.audio.AudioControl;
import lombok.NonNull;

/**
 * 语音对象Api
 */
public interface AudioApi {

    /**
     * 音频控制 Api
     * 频接口：仅限音频类机器人才能使用，后续会根据机器人类型自动开通接口权限，现如需调用，需联系平台申请权限。
     *
     * @param bot_id       机器人对象
     * @param channel_id   子频道ID
     * @param audioControl 音频控制对象
     * @return 操作结果
     */
    @EDoc(doc = "音频控制")
    Boolean audioControl
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull
            String channel_id,

            @EDoc(doc = "音频控制对象")
            @NonNull @ENonNull
            AudioControl audioControl
    );

    /**
     * 机器人上麦
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @return 返回操作结果
     */
    @EDoc(doc = "机器人上麦")
    Boolean singStart
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id
    );

    /**
     * 机器人下麦
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @return 操作结果
     */
    @EDoc(doc = "机器人下麦")
    Boolean singEnd
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id
    );
}
