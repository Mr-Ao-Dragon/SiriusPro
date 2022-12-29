package cn.siriusbot.siriuspro.task;


import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.websocket.WebSocketUtils;
import java.util.TimerTask;

public class HeartBeatTask extends TimerTask {
    public String botId;
    private SiriusBotClient siriusBotClient;

    public HeartBeatTask(String botId){
        this.botId = botId;
        this.siriusBotClient = BotManager.getBotByBotId(botId);
    }
    @Override
    public void run() {
        siriusBotClient.getWebSocketClient().send(WebSocketUtils.getHeartBeatPack(siriusBotClient));
    }
}
