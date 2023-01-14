package cn.siriusbot.siriuspro.bot.client;

import cn.siriusbot.siriuspro.bot.event.BotHttpEvent;

public interface BotConfigBuilder {

    /**
     * 首要配置事件类
     * @return
     */
    BotHttpEvent getBotHttpEvent();

    /**
     * 其他事件类添加
     * @param client
     */
    void builder(BotClient client);
}
