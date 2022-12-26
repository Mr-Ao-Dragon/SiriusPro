package cn.siriusbot.siriuspro.entity.impl.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 富文本-文本段落对象
 */
@Data
@Accessors(chain = true)
public class TextProps {
    /**
     * 是否加粗
     */
    private Boolean font_bold;

    /**
     * 是否斜体
     */
    private Boolean italic;

    /**
     * 是否下划线
     */
    private Boolean underline;
}
