package cn.siriusbot.siriuspro.entity.api;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.entity.impl.audio.AudioControl;

/**
 * 语音对象Api
 */
public interface AudioApi {

    /**
     * 音频控制 Api
     * 频接口：仅限音频类机器人才能使用，后续会根据机器人类型自动开通接口权限，现如需调用，需联系平台申请权限。
     *
     * @param bot          机器人对象
     * @param channel_id   子频道ID
     * @param audioControl 音频控制对象
     * @return 操作结果
     */
    public abstract Boolean audioControl(Bot bot, String channel_id, AudioControl audioControl);

    /**
     * 机器人上麦
     * @param bot 传入机器人对象
     * @param channel_id 子频道ID
     * @return 返回操作结果
     */
    public abstract Boolean singStart(Bot bot,String channel_id);

    /**
     * 机器人下麦
     * @param bot 传入机器人对象
     * @param channel_id 子频道ID
     * @return 操作结果
     */
    public abstract  Boolean singEnd(Bot bot,String channel_id);
}
