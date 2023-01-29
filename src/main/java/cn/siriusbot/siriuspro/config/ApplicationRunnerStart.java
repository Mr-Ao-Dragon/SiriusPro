package cn.siriusbot.siriuspro.config;

import cn.siriusbot.siriuspro.bot.BotApi;
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


        /*BotClient botClient = new SiriusBotClient(
                new BotInfo()
                        .setBotId("102004321")
                        .setToken("VwUd3zkSBZIbLlWZOXNlhrBsZDCtn6Dn")
                        .setBotType(BotType.PUBLIC_TYPE)
                        .setSandBox(true),
                botConfig
        );
        botClient.setConfig(
                IntentsEvent.class, new IntentsEventImpl()
                        .setIntents(IntentsType.PUBLIC_ALL)
        );
        botClient.start();

        BotClient botClient2 = new SiriusBotClient(
                new BotInfo()
                        .setBotId("101990484")
                        .setToken("vPavLsOHhJT90lUgo8SwAavbalnFzJMN")
                        .setBotType(BotType.PUBLIC_TYPE)
                        .setSandBox(true),
                botConfig
        );
        botClient2.setConfig(
                IntentsEvent.class, new IntentsEventImpl()
                        .setIntents(IntentsType.PUBLIC_ALL)
        );
        botClient2.start();
        System.out.println("启动完成");*/

    }
}
