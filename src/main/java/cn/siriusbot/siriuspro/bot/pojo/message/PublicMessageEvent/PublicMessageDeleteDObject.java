package cn.siriusbot.siriuspro.bot.pojo.message.PublicMessageEvent;

import cn.siriusbot.siriuspro.bot.api.pojo.message.Message;
import cn.siriusbot.siriuspro.bot.pojo.message.Op_User;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 公域消息被撤回事件详情对象
 */
@Data
@Accessors(chain = true)
public class PublicMessageDeleteDObject {

    /**
     * 被撤回的消息对象详情
     */
    Message message;

    /**
     * 撤回消息的人
     */
    Op_User op_user;
}
