package cn.siriusbot.siriuspro.config;

import cn.siriusbot.siriuspro.admin.dao.RobotMapper;
import cn.siriusbot.siriuspro.admin.entity.Robot;
import cn.siriusbot.siriuspro.application.ApplicationManager;
import cn.siriusbot.siriuspro.application.ApplicationUtils;
import cn.siriusbot.siriuspro.bot.BotToken;
import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.BotManager;
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


    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 项目初始化
        Sirius.SiriusInit();
        log.info("框架初始化中...");
        manager.loadApps();

        SiriusBotClient siriusBotClient = new SiriusBotClient("102003612", "u0vDKGGUPaqYyfx65tC0FaZru9DJQ7VH", BotToken.botType.PUBLIC_TYPE, false);
        botManager.AuthBot(siriusBotClient);
        botManager.loginBot(siriusBotClient.getInfo().getBotId());
        Thread.sleep(2000);
        botManager.logoutBot(siriusBotClient.getInfo().getBotId());

//        SiriusBotClient siriusBotClient2 = new SiriusBotClient("102007895", "vWFp0pHlCDur8yhum8A8JI5t41Gdkkp9", BotToken.botType.PUBLIC_TYPE, false);
//        BotManager.AuthBot(siriusBotClient2);
//        BotManager.loginBot(siriusBotClient2.getInfo().getBotId());
    }
}
