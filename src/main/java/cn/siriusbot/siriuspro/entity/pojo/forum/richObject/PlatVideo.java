package cn.siriusbot.siriuspro.entity.pojo.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 富文本-平台视频属性
 */
@Data
@Accessors(chain = true)
public class PlatVideo {
    /**
     * 架平图片链接
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
     * 视频ID
     */
    private String video_id;

    /**
     * 视频时长
     */
    private Integer duration;

    /**
     * 视频封面属性
     */
    private PlatImage cover;
}
