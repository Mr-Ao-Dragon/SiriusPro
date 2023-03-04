package cn.siriusbot.siriuspro.bot.api.pojo.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 富文本-段落结构对象
 */
@Data
@Accessors(chain = true)
public class Paragraph {
    /**
     * 元素列表
     */
    private List<Elem> elems;

    /**
     * 段落属性
     */
    private ParagraphProps props;
}
