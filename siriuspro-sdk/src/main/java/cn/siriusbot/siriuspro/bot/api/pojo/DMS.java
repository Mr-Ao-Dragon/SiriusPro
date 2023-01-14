package cn.siriusbot.siriuspro.bot.api.pojo;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
/**
 * 私信会话对象
 */
public class DMS {
    /**
     * 私信会话关联的频道ID
     */
    private String guild_id;

    /**
     * 私信会话关联的子频道ID
     */
    private String channel_id;

    /**
     * 私信会话的创建时间戳
     */
    private String create_time;


}
