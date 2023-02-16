package cn.siriusbot.siriuspro.config.bean;

import cn.siriusbot.siriuspro.admin.entity.Robot;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.pojo.BotInfo;
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
    Map<String, BotInfo> errorInfo = new ConcurrentHashMap<>(); // 记录无法正常创建对象的异常消息

    public void addBot(BotClient client){
        botClientMap.put(client.getInfo().getBotId(), client);
    }

    public void addErrorBot(BotInfo info){
        errorInfo.put(info.getBotId(), info);
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
     */
    public boolean botWhetherThereIs(String botId){
        return botClientMap.containsKey(botId);
    }

    public BotInfo queryBotInfoByBotId(String botId){
        if (botClientMap.containsKey(botId)){
            BotClient client = botClientMap.get(botId);
            return client.getInfo();
        }
        if (errorInfo.containsKey(botId)){
            return errorInfo.get(botId);
        }
        return null;
    }

    public List<BotInfo> queryOnLineBotList(){
        List<BotInfo> botInfos = new ArrayList<>();
        for (String key : this.botClientMap.keySet()){
            BotClient client = this.botClientMap.get(key);
            if (client.getInfo().getState() == Robot.STATE_ONLINE){
                botInfos.add(client.getInfo());
            }
        }
        return botInfos;
    }

    public List<BotClient> getAllClient(){
        return new ArrayList<>(botClientMap.values());
    }
}
