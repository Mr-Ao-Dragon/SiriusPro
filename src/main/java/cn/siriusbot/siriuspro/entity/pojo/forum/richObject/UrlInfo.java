package cn.siriusbot.siriuspro.entity.pojo.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 富文本里的链接信息对象
 */
@Data
@Accessors(chain = true)
public class UrlInfo {

    /**
     * 链接地址
     */
    private String url;


    /**
     * 链接显示文本
     */
    private String display_text;
}
