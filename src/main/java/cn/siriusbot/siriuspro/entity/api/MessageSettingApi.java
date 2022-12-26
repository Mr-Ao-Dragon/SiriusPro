package cn.siriusbot.siriuspro.entity.api;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.entity.impl.MessageSetting;
import cn.siriusbot.siriuspro.entity.temp.Tuple;

import java.util.Map;

/**
 * 频道消息频率设置对象Api
 */
public interface MessageSettingApi {

    /**
     * 获取频道消息频率设置信息
     * 用于获取机器人在频道 guild_id 内的消息频率设置。
     *
     * @param bot      传入机器人对象
     * @param guild_id 频道ID
     * @return 返回频道消息频率设置对象
     */
    public abstract Tuple<MessageSetting,String> getMessageSettingInfo(Bot bot, String guild_id);
}
