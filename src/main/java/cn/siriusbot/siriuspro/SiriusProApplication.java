package cn.siriusbot.siriuspro;

import cn.siriusbot.siriuspro.bot.api.impl.MessageImpl;
import cn.siriusbot.siriuspro.bot.api.pojo.message.embed.MessageEmbed;
import cn.siriusbot.siriuspro.bot.api.pojo.message.embed.MessageEmbedField;
import cn.siriusbot.siriuspro.bot.api.pojo.message.embed.MessageEmbedThumbnail;
import lombok.SneakyThrows;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication

@MapperScan("cn.siriusbot.siriuspro.admin.dao")
@EnableAsync
public class SiriusProApplication {
    public static void main(String[] args) {
        SpringApplication.run(SiriusProApplication.class, args);
    }

}
