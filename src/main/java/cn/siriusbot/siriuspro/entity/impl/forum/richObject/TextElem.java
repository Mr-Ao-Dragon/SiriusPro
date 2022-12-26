package cn.siriusbot.siriuspro.entity.impl.forum.richObject;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 富文本-文本属性对象
 */
@Data
@Accessors(chain = true)
public class TextElem {

    /**
     * 正文内容
     */
    private String text;

    /**
     * 文本属性对象
     */
    private TextProps props;
}
