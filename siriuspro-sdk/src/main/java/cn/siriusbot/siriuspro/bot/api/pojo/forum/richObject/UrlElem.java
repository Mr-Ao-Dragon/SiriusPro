package cn.siriusbot.siriuspro.bot.api.pojo.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 富文本-URL属性对象
 */
@Data
@Accessors(chain = true)
public class UrlElem {
    /**
     * URL链接
     */
    private String url;

    /**
     * URL描述
     */
    private String desc;
}
