package cn.siriusbot.siriuspro.entity.pojo;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PinsMessage {
    /**
     * 频道ID
     */
    private String guild_id;

    /**
     * 子频道ID
     */
    private String channel_id;

    /**
     * 子频道内精华消息id数组
     */
    private List<String> message_ids;



}
