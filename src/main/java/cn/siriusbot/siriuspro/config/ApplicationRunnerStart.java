package cn.siriusbot.siriuspro.config;

import cn.siriusbot.siriuspro.application.ApplicationManager;
import cn.siriusbot.siriuspro.bot.BotApi;
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
    BotConfig botConfig;

    @Autowired
    BotApi botApi;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 项目初始化
        Sirius.SiriusInit();
        log.info("框架初始化中...");

        //SiriusBotClient siriusBotClient = new SiriusBotClient("101990484", "vPavLsOHhJT90lUgo8SwAavbalnFzJMN", BotToken.botType.PUBLIC_TYPE, true);
        //botManager.AuthBot(siriusBotClient);
        //botManager.loginBot(siriusBotClient.getInfo().getBotId());

//        SiriusBotClient siriusBotClient2 = new SiriusBotClient("102007895", "vWFp0pHlCDur8yhum8A8JI5t41Gdkkp9", BotToken.botType.PUBLIC_TYPE, false);
//        BotManager.AuthBot(siriusBotClient2);
//        BotManager.loginBot(siriusBotClient2.getInfo().getBotId());


        BotClient botClient = new SiriusBotClient(
                new BotInfo()
                        .setBotId("102004321")
                        .setToken("VwUd3zkSBZIbLlWZOXNlhrBsZDCtn6Dn")
                        .setBotType(BotType.PUBLIC_TYPE)
                        .setSandBox(false),
                botConfig
        );
        botClient.setConfig(
                IntentsEvent.class, new IntentsEventImpl()
                        .setIntents(IntentsType
                                .PUBLIC_ALL)
        );
        botClient.start();

        BotClient botClient2 = new SiriusBotClient(
                new BotInfo()
                        .setBotId("101990484")
                        .setToken("vPavLsOHhJT90lUgo8SwAavbalnFzJMN")
                        .setBotType(BotType.PUBLIC_TYPE)
                        .setSandBox(false),
                botConfig
        );
        botClient2.setConfig(
                IntentsEvent.class, new IntentsEventImpl()
                        .setIntents(IntentsType.DIRECT_MESSAGE)
        );
        botClient2.start();

        BotClient botClient3 = new SiriusBotClient(
                new BotInfo()
                        .setBotId("102003612")
                        .setToken("u0vDKGGUPaqYyfx65tC0FaZru9DJQ7VH")
                        .setBotType(BotType.PRIVATE_TYPE)
                        .setSandBox(true),
                botConfig
        );
        botClient3.setConfig(
                IntentsEvent.class,
                new IntentsEventImpl()
                        .setIntents(IntentsType.ALL)

        );
        botClient3.start();

        BotClient botClient4 = new SiriusBotClient(
                new BotInfo()
                        .setBotId("101983723")
                        .setToken("sIScEGv7TdbqsQvJ6ESiLvIJcLdNR4fg")
                        .setBotType(BotType.PUBLIC_TYPE)
                        .setSandBox(true),
                botConfig
        );
        botClient4.setConfig(
                IntentsEvent.class,
                new IntentsEventImpl()
                        .setIntents(IntentsType.PUBLIC_ALL)
        );
        botClient4.start();
        System.out.println("启动完成");

    }
}
