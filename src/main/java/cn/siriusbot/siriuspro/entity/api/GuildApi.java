package cn.siriusbot.siriuspro.entity.api;


import cn.siriusbot.siriuspro.entity.pojo.Guild;
import cn.siriusbot.siriuspro.entity.temp.Tuple;

/**
 * 频道Api
 */
public interface GuildApi {

    /**
     * 获取频道详情
     * @param bot_id 传入机器人ID
     * @param guild_id 频道ID
     * @return 返回频道详情对象
     */
    public abstract Tuple<Guild,String> getGuildInfo(String bot_id, String guild_id);
}
