package cn.siriusbot.siriuspro.config.bean;

import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.error.MsgException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 机器人对象池
 */
@Component
public class BotPool {
    Map<String, BotClient> botClientMap = new ConcurrentHashMap<>();

    public void addBot(BotClient client){
        botClientMap.put(client.getInfo().getBotId(), client);
    }

    public void remove(String botId){
        botClientMap.remove(botId);
    }

    public BotClient getBotById(String botId){
        if (!botClientMap.containsKey(botId)){
            throw new MsgException(500, "Bot对象不存在！");
        }
        return botClientMap.get(botId);
    }
}