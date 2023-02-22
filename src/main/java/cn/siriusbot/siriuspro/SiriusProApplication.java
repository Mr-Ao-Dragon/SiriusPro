package cn.siriusbot.siriuspro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication

@MapperScan("cn.siriusbot.siriuspro.admin.dao")
@EnableAsync
public class SiriusProApplication {
    public static void main(String[] args) {
        SpringApplication.run(SiriusProApplication.class, args);
    }

}
