package cn.siriusbot.siriuspro.config;

import cn.siriusbot.siriuspro.application.ApplicationManager;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.client.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.event.IntentsEvent;
import cn.siriusbot.siriuspro.bot.event.impl.IntentsEventImpl;
import cn.siriusbot.siriuspro.bot.pojo.BotInfo;
import cn.siriusbot.siriuspro.bot.pojo.e.BotType;
import cn.siriusbot.siriuspro.bot.pojo.e.IntentsType;
import cn.siriusbot.siriuspro.config.bean.BotConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ApplicationRunnerStart implements ApplicationRunner {

    @Autowired
    ApplicationManager manager;

    @Autowired
    BotManager botManager;

    @Autowired
    BotConfig botConfig;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 项目初始化
        Sirius.SiriusInit();
        log.info("框架初始化中...");
        manager.loadApps();

        //SiriusBotClient siriusBotClient = new SiriusBotClient("101990484", "vPavLsOHhJT90lUgo8SwAavbalnFzJMN", BotToken.botType.PUBLIC_TYPE, true);
        //botManager.AuthBot(siriusBotClient);
        //botManager.loginBot(siriusBotClient.getInfo().getBotId());

//        SiriusBotClient siriusBotClient2 = new SiriusBotClient("102007895", "vWFp0pHlCDur8yhum8A8JI5t41Gdkkp9", BotToken.botType.PUBLIC_TYPE, false);
//        BotManager.AuthBot(siriusBotClient2);
//        BotManager.loginBot(siriusBotClient2.getInfo().getBotId());


        BotClient botClient = new SiriusBotClient(
                new BotInfo()
                        .setBotId("101997967")
                        .setToken("BecMX9rY8hhFzKPecZIOWisnTXSIBTEd")
                        .setBotType(BotType.PUBLIC_TYPE)
                        .setSandBox(false),
                botConfig
        );
        botClient.setConfig(
                IntentsEvent.class, new IntentsEventImpl()
                        .setIntents(IntentsType.GUILDS)
                        .setIntents(IntentsType.AUDIO_ACTION)
                        .setIntents(IntentsType.PUBLIC_ALL)
        );
        botClient.start();
        System.out.println("启动完成");
    }
}
