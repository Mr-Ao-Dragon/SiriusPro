package cn.siriusbot.siriuspro.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@ComponentScan
@PropertySource(value="file:${user.dir}/conf/database.properties",ignoreResourceNotFound = true)
@Log4j2
public class DataSourceConfig {

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(this.driver);
        dataSourceBuilder.username(this.username);
        dataSourceBuilder.password(this.password);
        dataSourceBuilder.url(this.url);

        return dataSourceBuilder.build();
    }

}