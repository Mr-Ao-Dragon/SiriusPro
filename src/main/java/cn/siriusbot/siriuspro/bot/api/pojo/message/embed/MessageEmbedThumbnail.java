package cn.siriusbot.siriuspro.bot.api.pojo.message.embed;

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