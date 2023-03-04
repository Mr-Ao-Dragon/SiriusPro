package cn.siriusbot.siriuspro.bot.api.pojo.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 富文本-图片属性对象
 */
@Data
@Accessors(chain = true)
public class ImageElem {

    /**
     * 第三方图片链接
     */
    private ImageElemInfo plat_image;

    /**
     * 宽度比例
     */
    private Double width_percent;
}
