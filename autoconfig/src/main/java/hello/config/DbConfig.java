package hello.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@Slf4j
//@Configuration
public class DbConfig {

    @Bean
    public DataSource dataSource() {
        log.info("dataSource bean 등록");
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setJdbcUrl("jdbc:h2:mem:test"); // in-memory DB
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public TransactionManager transactionManager() {
        log.info("transactionManager bean 등록");
        return new JdbcTransactionManager(dataSource()); // DataSourceTransactionManager 와 같지만 기능이 더 많음
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        log.info("jdbcTemplate bean 등록");
        return new JdbcTemplate(dataSource());
    }
}
