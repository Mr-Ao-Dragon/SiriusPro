package cn.siriusbot.siriuspro.bot.pojo.message.AudioMessageEvent;

import cn.siriusbot.siriuspro.bot.pojo.message.MessageBody;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 音频事件详情对象
 */
@Data
@Accessors(chain = true)
public class AudioMessageEventDObject implements MessageBody {
    /**
     * 音频URL
     */
    String music_url;

    /**
     * 频道ID
     */
    String guild_id;

    /**
     * 状态文本
     */
    String text;

    /**
     * 子频道ID
     */
    String channel_id;
}
