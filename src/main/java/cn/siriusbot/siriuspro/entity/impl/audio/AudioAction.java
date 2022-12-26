package cn.siriusbot.siriuspro.entity.impl.audio;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 音频操作对象
 */
@Data
@Accessors(chain = true)
public class AudioAction {

    /**
     * 频道ID
     */
    private String guild_id;

    /**
     * 子频道ID
     */
    private String channel_id;

    /**
     * 音频数据的url,status为0时传入
     */
    private String audio_url;

    /**
     * 状态文本
     */
    private String text;
}
