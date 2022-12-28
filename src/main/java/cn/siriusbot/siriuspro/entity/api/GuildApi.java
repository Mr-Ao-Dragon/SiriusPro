package cn.siriusbot.siriuspro.entity.api;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.entity.pojo.Guild;
import cn.siriusbot.siriuspro.entity.temp.Tuple;

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
    public abstract Tuple<Guild,String> getGuildInfo(Bot bot, String guild_id);
}
