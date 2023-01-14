package cn.siriusbot.siriuspro.bot.event.v1;

import cn.siriusbot.siriuspro.bot.pojo.e.BotEventType;

public interface EventMethodNoParam {
    void onEvent(BotEventType type);
}
