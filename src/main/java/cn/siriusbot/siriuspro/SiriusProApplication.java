package cn.siriusbot.siriuspro;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.impl.audio.AudioControl;
import cn.siriusbot.siriuspro.entity.impl.message.Message;
import cn.siriusbot.siriuspro.message.MessageManager;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SiriusProApplication {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(SiriusProApplication.class, args);
        Bot bot = new Bot("102004321", "VwUd3zkSBZIbLlWZOXNlhrBsZDCtn6Dn", Bot.botType.PUBLIC_TYPE, true);
        BotManager.AuthBot(bot);
        BotManager.loginBot(bot.getBotId());
        Thread.sleep(2000);
//        AudioControl audioControl = new AudioControl();
//        audioControl.setAudio_url("http://home.siriusbot.cn/test.mp3").setStatus(AudioControl.STATUS.START.getValue()).setText("测试播放");
//        new AudioControl().audioControl(bot, "39137206",audioControl);
        new Message().sendMessage(bot,"6034803","测试",null,null,null);
    }

}
