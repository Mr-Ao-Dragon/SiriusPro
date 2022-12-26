package cn.siriusbot.siriuspro.application;

import cn.siriusbot.siriuspro.message.PublicMessageEvent.PublicMessageEvent;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 天狼星应用模板
 */
@Data
@Accessors(chain = true)
public abstract class SiriusApplication {


    /**
     * 应用详情
     */
    public SiriusApplicationInfo appInfo = new SiriusApplicationInfo();


    /**
     * 公域消息事件
     * @param bot_id 来源机器人id
     * @param event 公域消息事件对象
     */
    public abstract void public_message_event(String bot_id, PublicMessageEvent event);
}
