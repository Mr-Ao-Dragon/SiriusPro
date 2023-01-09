package cn.siriusbot.siriuspro;

import cn.siriusbot.siriuspro.application.ApplicationManager;
import cn.siriusbot.siriuspro.application.ApplicationUtils;
import cn.siriusbot.siriuspro.config.Sirius;
import cn.siriusbot.siriuspro.config.SiriusUtils;
import lombok.SneakyThrows;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("cn.siriusbot.siriuspro.admin.dao")
@EnableAsync
public class SiriusProApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiriusProApplication.class, args);
    }

}
