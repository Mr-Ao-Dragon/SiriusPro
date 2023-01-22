package cn.siriusbot.siriuspro.bot.pojo.message.DirectMessageEvent;

import cn.siriusbot.siriuspro.bot.api.pojo.message.Message;
import cn.siriusbot.siriuspro.bot.pojo.message.Op_User;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 私信消息撤回事件详情对象
 */
@Data
@Accessors(chain = true)
public class DirectMessageDeleteDObject {

    /**
     * 被撤回的消息事件对象
     */
    private Message message;

    /**
     * 操作人对象
     */
    private Op_User op_user;
}
