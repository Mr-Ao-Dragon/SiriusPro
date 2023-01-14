package cn.siriusbot.siriuspro.bot.pojo.event;

import cn.siriusbot.siriuspro.bot.pojo.message.MessageBody;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BotEventMessage implements BotEventBody {
    /**
     * 会话消息id
     */
    int s;

    /**
     * 原始数据
     */
    String message;

    /**
     * 事件类型
     */
    String eventType;

    /**
     * 消息类型
     */
    Class<? extends MessageBody> clazz;

    /**
     * 消息对象
     */
    Object object;
}
