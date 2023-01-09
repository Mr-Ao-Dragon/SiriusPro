package cn.siriusbot.siriuspro.websocket;

import cn.siriusbot.siriuspro.bot.BotToken;
import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.task.HeartBeatTask;
import cn.siriusbot.siriuspro.uitls.AppContextUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class WebSocketUtils {

    /**
     * 生成鉴权包
     *
     * @param siriusBotClient 传入机器人对象
     * @return 返回用于鉴权的数据
     */
    public static String getAuthPack(SiriusBotClient siriusBotClient) {
        JSONObject rootObject = new JSONObject();
        rootObject.put("op", 2);
        JSONObject dObject = new JSONObject();
        dObject.put("token", siriusBotClient.getInfo().getBotId() + "." + siriusBotClient.getInfo().getToken());
        if (siriusBotClient.getInfo().getBotType() == BotToken.botType.PUBLIC_TYPE) {
            dObject.put("intents",1812730883);
        }else if(siriusBotClient.getInfo().getBotType()== BotToken.botType.PRIVATE_TYPE){
            dObject.put("intents",2081166851);
        }
        dObject.put("shard",null);
        dObject.put("properties",null);
        rootObject.put("d",dObject);
        return rootObject.toJSONString();
    }

    /**
     * 生成心跳包
     * @param siriusBotClient 传入机器人对象
     * @return 返回用于发送心跳包的数据
     */
    public static String getHeartBeatPack(SiriusBotClient siriusBotClient){
        JSONObject json = new JSONObject();
        json.put("op",1);
        json.put("d", siriusBotClient.getSocket().getS());
        return json.toJSONString();
    }

    /**
     * 生成重连包
     * @param siriusBotClient 传入机器人对象
     * @return 返回用于重连的数据
     */
    public static String getReconnectPack(SiriusBotClient siriusBotClient){
        JSONObject json = new JSONObject();
        JSONObject dObject = new JSONObject();
        json.put("op",6);
        dObject.put("token", siriusBotClient.getInfo().getBotId()+"."+ siriusBotClient.getInfo().getToken());
        dObject.put("session_id", siriusBotClient.getSocket().getSession_id());
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
        BotManager botManager = AppContextUtil.getBean(BotManager.class);
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(botId);
        siriusBotClient.getSocket().getHeartBeatTimer().pause();
        siriusBotClient.setWebSocketClient(new SiriusWebSocketClient(siriusBotClient, siriusBotClient.getSocket().getWebSocketUri()));
        siriusBotClient.getWebSocketClient().connect();
    }

    /**
     * 已恢复连接
     *
     * @param botId 传入机器人ID
     */
    public static void Resume(String botId) {
        BotManager botManager = AppContextUtil.getBean(BotManager.class);
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(botId);
        siriusBotClient.getSocket().getHeartBeatTimer().resume(new Date(), new HeartBeatTask(siriusBotClient.getInfo().getBotId()));
    }
}
