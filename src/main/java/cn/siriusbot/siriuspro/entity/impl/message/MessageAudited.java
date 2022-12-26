package cn.siriusbot.siriuspro.entity.impl.message;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
/**
 * 消息审核对象
 */
public class MessageAudited{
    /**
     * 消息审核id
     */
    private  String audit_id;
    /**
     * 消息id，只有审核通过才会有值
     */
    private String message_id;
    /**
     * 子频道ID
     */
    private String channel_id;
    /**
     * 消息审核时间
     */
    private String audit_time;
    /**
     * 消息创建时间
     */
    private String create_time;
    /**
     * 子频道消息seq，用于消息排序，不同子频道的消息无法排序
     */
    private String seq_in_channel;
}