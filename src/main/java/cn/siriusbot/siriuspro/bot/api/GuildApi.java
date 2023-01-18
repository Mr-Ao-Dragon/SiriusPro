package cn.siriusbot.siriuspro.bot.api;


import cn.siriusbot.siriuspro.bot.annotation.EDoc;
import cn.siriusbot.siriuspro.bot.annotation.ENonNull;
import cn.siriusbot.siriuspro.bot.api.pojo.Guild;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import lombok.NonNull;

/**
 * 频道Api
 */
public interface GuildApi {

    /**
     * 获取频道详情
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @return 返回频道详情对象
     */
    @EDoc(doc = "获取频道详情")
    Tuple<Guild, String> getGuildInfo
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull String guild_id
    );
}
