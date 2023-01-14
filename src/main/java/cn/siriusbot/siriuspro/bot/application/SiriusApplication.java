package cn.siriusbot.siriuspro.bot.application;

import cn.siriusbot.siriuspro.bot.BotApi;

/**
 * 天狼星应用模板
 */
public interface SiriusApplication {

    default int versions(){
        return 2;   // 版本号
    }

    /**
     * 插件初始化
     */
    void SiriusAppInit(BotApi api);

    /**
     * 应用详情
     */
    SiriusApplicationInfo appInfo();
}
