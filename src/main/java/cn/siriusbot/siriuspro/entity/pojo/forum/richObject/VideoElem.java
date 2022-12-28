package cn.siriusbot.siriuspro.entity.pojo.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 富文本-视频属性对象
 */
@Data
@Accessors(chain = true)
public class VideoElem {
    /**
     * 第三方视频文件链接
     */
    private String third_url;
}
