package cn.siriusbot.siriuspro.bot.event;

import cn.siriusbot.siriuspro.bot.event.v1.BotEvent;
import cn.siriusbot.siriuspro.bot.pojo.e.IntentsType;

/**
 * 授权自定义监听事件
 */
public interface IntentsEvent extends BotEvent {

    IntentsEvent setIntents(IntentsType intents);

}
