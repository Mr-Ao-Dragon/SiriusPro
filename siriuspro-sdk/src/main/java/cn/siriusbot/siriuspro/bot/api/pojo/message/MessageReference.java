package cn.siriusbot.siriuspro.bot.api.pojo.message;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
/**
 * 引用消息对象
 */
public class MessageReference {
    /**
     * 需要引用回复消息的 id
     */
    private String message_id;
    /**
     * 是否忽略获取引用消息详情错误，默认否
     */
    private boolean ignore_get_message_error;
}