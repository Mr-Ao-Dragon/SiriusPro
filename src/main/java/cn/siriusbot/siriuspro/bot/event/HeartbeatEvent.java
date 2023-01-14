package cn.siriusbot.siriuspro.bot.event;

import cn.siriusbot.siriuspro.bot.event.v1.BotEvent;

/**
 * 心跳事件
 */
public interface HeartbeatEvent extends BotEvent {
    /**
     * 运行任务
     */
    void run();

    /**
     * 暂停
     */
    void pause();

}
