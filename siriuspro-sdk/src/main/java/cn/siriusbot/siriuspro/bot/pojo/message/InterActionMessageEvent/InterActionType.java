package cn.siriusbot.siriuspro.bot.pojo.message.InterActionMessageEvent;

import cn.siriusbot.siriuspro.bot.pojo.message.MessageBody;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 不知道干嘛的
 */
@Accessors(chain = true)
@Data
public class InterActionType implements MessageBody {
    /**
     * 类型
     */
    private Integer type;


    /**
     * 主要数据
     */
    private Resolved resolved;
}
