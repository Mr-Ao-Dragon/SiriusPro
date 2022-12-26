package cn.siriusbot.siriuspro.entity.impl.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 富文本里的子频道信息对象
 */
@Data
@Accessors(chain = true)
public class ChannelInfo {

    /**
     * 子频道ID
     */
    private Long channel_id;

    /**
     * 子频道名称
     */
    private String channel_name;
}
