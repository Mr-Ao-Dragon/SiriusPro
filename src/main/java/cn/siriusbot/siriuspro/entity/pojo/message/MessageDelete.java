package cn.siriusbot.siriuspro.entity.pojo.message;

import cn.siriusbot.siriuspro.entity.pojo.User;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
/**
 * 被删除的消息对象
 */
public class MessageDelete {
    /**
     * 被删除的消息对象
     */
    private Message message;
    /**
     * User 执行删除操作的用户
     */
    private User user;
}