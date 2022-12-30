package cn.siriusbot.siriuspro.entity.pojo.message;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
/**
 * 附件消息对象
 */
public class MessageAttachment {
    /**
     * 附件下载地址
     */
    private String url;
}