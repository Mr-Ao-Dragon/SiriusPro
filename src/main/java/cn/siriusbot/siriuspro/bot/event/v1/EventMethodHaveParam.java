package cn.siriusbot.siriuspro.bot.event.v1;

import cn.siriusbot.siriuspro.bot.pojo.e.BotEventType;
import cn.siriusbot.siriuspro.bot.pojo.event.BotEventBody;

/**
 * 以更安全的方式构造接收bot事件
 * @param <T> bot事件body对象
 */
public interface EventMethodHaveParam<T extends BotEventBody>{
    void onEvent(BotEventType type, T body);
}
