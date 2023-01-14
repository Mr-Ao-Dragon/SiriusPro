package cn.siriusbot.siriuspro.bot.api.pojo.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 富文本-平台图片属性
 */
@Data
@Accessors(chain = true)
public class PlatImage {
    /**
     * 架平(不懂架平是什么意思)图片链接
     */
    private String url;

    /**
     * 图片宽度
     */
    private Integer width;

    /**
     * 图片高度
     */
    private Integer height;

    /**
     * 图片ID
     */
    private String image_id;
}
