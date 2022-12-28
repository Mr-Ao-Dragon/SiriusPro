package cn.siriusbot.siriuspro.entity.pojo.message.requestPack;

import cn.siriusbot.siriuspro.entity.pojo.message.MessageKeyboard;
import cn.siriusbot.siriuspro.entity.pojo.message.MessageMarkdown;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用于发送自定义按钮消息对象的封装对象
 */
@Data
@Accessors(chain = true)
public class RequestCustomKeyboard {

    /**
     * markdown消息对象
     */
    private MessageMarkdown markdown;

    /**
     * 消息按钮对象
     */
    private MessageKeyboard messageKeyboard;

    /**
     * 机器人id
     */
    private String bot_appid;
}
