package cn.siriusbot.siriuspro.bot.api.pojo.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 艾特频道信息对象
 */
@Data
@Accessors(chain = true)
public class AtGuildInfo {

    /**
     * 频道ID
     */
    private String guild_id;

    /**
     * 频道名称
     */
    private String guild_name;
}
