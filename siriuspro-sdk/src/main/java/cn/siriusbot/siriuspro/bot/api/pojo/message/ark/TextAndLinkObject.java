package cn.siriusbot.siriuspro.bot.api.pojo.message.ark;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
/**
 * 文字链接Ark消息对象
 */
public class TextAndLinkObject {
    /**
     * 文本内容
     */
    String desc;

    /**
     * url地址
     */
    String link;
}