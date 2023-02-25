package cn.siriusbot.siriuspro;

import cn.siriusbot.siriuspro.config.Sirius;
import cn.siriusbot.siriuspro.uitls.SiriusLoggerUtil;
import org.fusesource.jansi.AnsiConsole;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication

@MapperScan("cn.siriusbot.siriuspro.admin.dao")
@EnableAsync
public class SiriusProApplication {
    public static void main(String[] args) {
        Sirius.SiriusInit();
        SpringApplication.run(SiriusProApplication.class, args);
    }

}
