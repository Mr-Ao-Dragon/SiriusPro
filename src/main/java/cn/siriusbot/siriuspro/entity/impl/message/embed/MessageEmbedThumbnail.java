package cn.siriusbot.siriuspro.entity.impl.message.embed;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * embed缩略图对象
 */
@Data
@Accessors(chain = true)
public class MessageEmbedThumbnail {
    /**
     * 图片地址
     */
    private String url;
}