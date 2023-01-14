package cn.siriusbot.siriuspro.bot.pojo.message.AuditMessageEvent;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 消息审核事件详情对象
 */
@Data
@Accessors(chain = true)
public class AuditMessageEventDObject {

    /**
     * 审核时间
     */
    String audit_time;

    /**
     * 审核ID
     */
    String audit_id;

    /**
     * 消息创建时间
     */
    String create_time;

    /**
     * 子频道消息排序
     */
    String seq_in_channel;

    /**
     * 频道ID
     */
    String guild_id;

    /**
     * 消息ID
     */
    String message_id;

    /**
     * 子频道ID
     */
    String channel_id;
}
