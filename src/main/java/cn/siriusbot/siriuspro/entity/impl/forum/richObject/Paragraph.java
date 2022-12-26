package cn.siriusbot.siriuspro.entity.impl.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 富文本-段落结构对象
 */
@Data
@Accessors(chain = true)
public class Paragraph {
    /**
     * 元素列表
     */
    private Elem elems;

    /**
     * 段落属性
     */
    private ParagraphProps props;
}
