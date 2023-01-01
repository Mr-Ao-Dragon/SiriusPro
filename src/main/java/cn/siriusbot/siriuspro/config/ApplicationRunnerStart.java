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


    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 项目初始化
        log.info("框架初始化中...");
        manager.loadApps();


        SiriusBotClient siriusBotClient = new SiriusBotClient("102004321", "VwUd3zkSBZIbLlWZOXNlhrBsZDCtn6Dn", BotToken.botType.PUBLIC_TYPE, false);
        BotManager.AuthBot(siriusBotClient);
        BotManager.loginBot(siriusBotClient.getInfo().getBotId());
    }
}
