package cn.siriusbot.siriuspro.entity.api;

import cn.siriusbot.siriuspro.entity.pojo.MessageSetting;
import cn.siriusbot.siriuspro.entity.temp.Tuple;

/**
 * 频道消息频率设置对象Api
 */
public interface MessageSettingApi {

    /**
     * 获取频道消息频率设置信息
     * 用于获取机器人在频道 guild_id 内的消息频率设置。
     *
     * @param bot_id      传入机器人ID
     * @param guild_id 频道ID
     * @return 返回频道消息频率设置对象
     */
    public abstract Tuple<MessageSetting,String> getMessageSettingInfo(String bot_id, String guild_id);
}
