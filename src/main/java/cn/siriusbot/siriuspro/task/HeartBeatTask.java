package cn.siriusbot.siriuspro.task;


import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.uitls.AppContextUtil;
import cn.siriusbot.siriuspro.websocket.WebSocketUtils;
import java.util.TimerTask;

public class HeartBeatTask extends TimerTask {
    public String botId;
    private final SiriusBotClient siriusBotClient;

    public HeartBeatTask(String botId){
        BotManager botManager = AppContextUtil.getBean(BotManager.class);
        this.botId = botId;
        this.siriusBotClient = botManager.getBotByBotId(botId);
    }
    @Override
    public void run() {
        siriusBotClient.getWebSocketClient().send(WebSocketUtils.getHeartBeatPack(siriusBotClient));
    }
}
