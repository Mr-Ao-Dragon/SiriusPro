package cn.siriusbot.siriuspro.entity.impl.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 富文本里的Emoji表情信息对象
 */
@Data
@Accessors(chain = true)
public class EmojiInfo {

    /**
     * 链接地址
     */
    private String url;

    /**
     * 链接显示文本呢
     */
    private String display_text;
}
