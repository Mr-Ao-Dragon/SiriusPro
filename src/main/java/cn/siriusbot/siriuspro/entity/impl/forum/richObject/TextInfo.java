package cn.siriusbot.siriuspro.entity.impl.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 富文本里的普通文本对象
 */
@Data
@Accessors(chain = true)
public class TextInfo {
    /**
     * 普通文本内容
     */
    private String text;
}
