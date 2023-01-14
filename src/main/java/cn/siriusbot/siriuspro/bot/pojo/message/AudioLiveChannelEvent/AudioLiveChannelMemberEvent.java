package cn.siriusbot.siriuspro.bot.pojo.message.AudioLiveChannelEvent;

import cn.siriusbot.siriuspro.bot.pojo.message.MessageBody;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 音视频/直播子频道成员进出事件
 */
@Data
@Accessors(chain = true)
public class AudioLiveChannelMemberEvent implements MessageBody {
    /**
     * op值
     */
    private Integer op;

    /**
     * webSocketClient消息排序
     */
    private Integer s;

    /**
     * 事件类型
     */
    private String t;

    /**
     * 事件对象详情
     */
    private AudioLiveChannelMemberEventDObject d;

    /**
     * 事件id
     */
    private String id;

    /**
     * 所属机器人id
     */
    private String bot_id;
}
