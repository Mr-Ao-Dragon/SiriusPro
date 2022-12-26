package cn.siriusbot.siriuspro.websocket;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.task.HeartBeatTask;
import com.alibaba.fastjson.JSONObject;
import org.java_websocket.drafts.Draft_6455;

import java.util.Date;

public class WebSocketUtils {

    /**
     * 生成鉴权包
     *
     * @param bot 传入机器人对象
     * @return 返回用于鉴权的数据
     */
    public static String getAuthPack(Bot bot) {
        JSONObject rootObject = new JSONObject();
        rootObject.put("op", 2);
        JSONObject dObject = new JSONObject();
        dObject.put("token", bot.getBotId() + "." + bot.getToken());
        if (bot.getBotType() == Bot.botType.PUBLIC_TYPE) {
            dObject.put("intents",1812730883);
        }else if(bot.getBotType()== Bot.botType.PRIVATE_TYPE){
            dObject.put("intents",2081166851);
        }
        dObject.put("shard",null);
        dObject.put("properties",null);
        rootObject.put("d",dObject);
        return rootObject.toJSONString();
    }

    /**
     * 生成心跳包
     * @param bot 传入机器人对象
     * @return 返回用于发送心跳包的数据
     */
    public static String getHeartBeatPack(Bot bot){
        JSONObject json = new JSONObject();
        json.put("op",1);
        json.put("d",bot.getS());
        return json.toJSONString();
    }

    /**
     * 生成重连包
     * @param bot 传入机器人对象
     * @return 返回用于重连的数据
     */
    public static String getReconnectPack(Bot bot){
        JSONObject json = new JSONObject();
        JSONObject dObject = new JSONObject();
        json.put("op",6);
        dObject.put("token",bot.getBotId()+"."+bot.getToken());
        dObject.put("session_id",bot.getSession_id());
        dObject.put("seq",2);
        json.put("d",dObject);
        return json.toJSONString();
    }

    /**
     * 重连请求
     *
     * @param botId 传入机器人ID
     */
    public static void Reconnect(String botId) {
        Bot bot = BotManager.getBotByBotId(botId);
        bot.getHeartBeatTimer().pause();
        bot.setWebSocketClient(new SiriusWebSocketClient(bot,bot.getWebSocketUri()));
        bot.getWebSocketClient().connect();
    }

    /**
     * 已恢复连接
     *
     * @param botId 传入机器人ID
     */
    public static void Resume(String botId) {
        Bot bot = BotManager.getBotByBotId(botId);
        bot.getHeartBeatTimer().resume(new Date(), new HeartBeatTask(bot.getBotId()));
    }
}
