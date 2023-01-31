package cn.siriusbot.siriuspro.config.bean;

import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.error.MsgException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    /**
     * 查询机器人是否存在
     * @param botId
     * @return
     */
    public boolean botWhetherThereIs(String botId){
        return botClientMap.containsKey(botId);
    }

    public List<BotClient> getAllClient(){
        return new ArrayList<>(botClientMap.values());
    }
}
