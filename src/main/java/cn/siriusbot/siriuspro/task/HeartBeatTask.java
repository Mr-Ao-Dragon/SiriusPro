package cn.siriusbot.siriuspro.task;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.websocket.WebSocketUtils;
import java.util.TimerTask;

public class HeartBeatTask extends TimerTask {
    public String botId;
    private Bot bot;

    public HeartBeatTask(String botId){
        this.botId = botId;
        this.bot = BotManager.getBotByBotId(botId);
    }
    @Override
    public void run() {
        bot.getWebSocketClient().send(WebSocketUtils.getHeartBeatPack(bot));
    }
}
