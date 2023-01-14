package cn.siriusbot.siriuspro.bot.event.v1;

import cn.siriusbot.siriuspro.bot.client.BotClient;

public interface BotEvent {
    /**
     * 注入客户端对象，并初始化
     * @param client
     */
    void init(BotClient client);

    /**
     * 机器人登录事件
     */
    void start();
}
