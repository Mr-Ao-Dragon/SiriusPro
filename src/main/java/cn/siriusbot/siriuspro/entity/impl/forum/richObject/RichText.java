package cn.siriusbot.siriuspro.entity.impl.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 富文本内容对象
 */
@Data
@Accessors(chain = true)
public class RichText {
    /**
     * 段落，一段落一行，段落内无元素的为空行
     */
    private Paragraph paragraphs;
}
