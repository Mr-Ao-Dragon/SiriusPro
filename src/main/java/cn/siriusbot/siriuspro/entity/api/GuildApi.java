package cn.siriusbot.siriuspro.entity.api;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.entity.impl.Guild;

import java.util.Map;

/**
 * 频道Api
 */
public interface GuildApi {

    /**
     * 获取频道详情
     * @param bot 传入机器人对象
     * @param guild_id 频道ID
     * @return 返回频道详情对象
     */
    public abstract Map<Guild,Object> getGuildInfo(Bot bot, String guild_id);
}
