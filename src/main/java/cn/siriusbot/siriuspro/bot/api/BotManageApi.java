package cn.siriusbot.siriuspro.bot.api;

import cn.siriusbot.siriuspro.bot.pojo.BotInfo;

import java.util.List;

public interface BotManageApi {
    /**
     * 获取在线的机器人列表
     * @return
     */
    List<BotInfo> queryOnLineBotList();

    /**
     * 写配置
     * @param key
     * @param val
     */
    void setServerConfig(String key, String val);

    /**
     * 读配置，不存在返回空字符串
     * @param key
     * @return
     */
    String getServerConfig(String key);
}
