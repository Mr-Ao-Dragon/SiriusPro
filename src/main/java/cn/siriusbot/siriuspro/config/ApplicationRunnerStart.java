package cn.siriusbot.siriuspro.config;

import cn.siriusbot.siriuspro.admin.service.BotService;
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

    @Autowired
    BotService  botService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 项目初始化
        log.info("框架初始化中...");

        log.info("机器人自动登录...");
        botService.autoLoginBot();
    }
}
