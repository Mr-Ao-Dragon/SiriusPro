package cn.siriusbot.siriuspro.bot.pojo.message.AudioLiveChannelEvent;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 音视频/直播子频道事件详情
 */
@Data
@Accessors(chain = true)
public class AudioLiveChannelMemberEventDObject {
    /**
     * 用户id
     */
    private String user_id;

    /**
     * 频道ID
     */
    private String guild_id;

    /**
     * 子频道类型:2-音视频子频道,5-直播子频道
     */
    private Integer channel_type;

    /**
     * 子频道ID
     */
    private String channel_id;
}
