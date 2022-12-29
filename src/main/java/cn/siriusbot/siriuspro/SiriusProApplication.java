package cn.siriusbot.siriuspro;

import cn.siriusbot.siriuspro.application.ApplicationManager;
import cn.siriusbot.siriuspro.application.ApplicationUtils;
import lombok.SneakyThrows;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("cn.siriusbot.siriuspro.admin")
public class SiriusProApplication {

    public static void main(String[] args) {
        ApplicationUtils.initAppPath();
        SpringApplication.run(SiriusProApplication.class, args);
    }

}
