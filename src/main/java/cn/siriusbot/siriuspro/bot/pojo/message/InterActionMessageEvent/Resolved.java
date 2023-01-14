package cn.siriusbot.siriuspro.bot.pojo.message.InterActionMessageEvent;

import cn.siriusbot.siriuspro.bot.pojo.message.MessageBody;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 按钮回调主要数据
 */
@Data
@Accessors(chain = true)
public class Resolved implements MessageBody {
    /**
     * 点击按钮的用户id
     */
    private String user_id;

    /**
     * 按钮id
     */
    private String button_id;

    /**
     * 消息ID
     */
    private String message_id;

    /**
     * 按钮附带数据
     */
    private String button_data;
}
