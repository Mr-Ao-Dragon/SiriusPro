package cn.siriusbot.siriuspro.bot.event;

import cn.siriusbot.siriuspro.bot.event.v1.BotEvent;

public interface WebSocketServiceEvent extends BotEvent {
    void sendAll(String message);
}
