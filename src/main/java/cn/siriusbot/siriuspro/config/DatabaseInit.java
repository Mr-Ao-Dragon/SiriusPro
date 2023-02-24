package cn.siriusbot.siriuspro.config;

import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@Log4j2
public class DatabaseInit {

    @Value("classpath:sql/sql.sql")
    private org.springframework.core.io.Resource sql;

    @Resource
    private DataSource dataSource;

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        // 设置数据源
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try {
            Map<String, Object> map = jdbcTemplate.queryForMap("select count(1) from `admin`");
        } catch (DataAccessException e) {
            // 报错,表不存在,初次导入，执行相关脚本
            populator.addScripts(sql);
        } catch (Exception e) {
            log.warn("初始化脚本报错:" + e.getMessage());
        }
        return populator;
    }

}
