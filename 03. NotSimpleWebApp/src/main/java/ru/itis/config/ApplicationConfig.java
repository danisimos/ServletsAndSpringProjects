package ru.itis.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import ru.itis.repositories.WordsRepository;
import ru.itis.repositories.impl.WordsRepositoryJdbcTemplateImpl;
import ru.itis.service.WordCounterService;

import javax.sql.DataSource;

@Configuration
@ComponentScan("ru.itis")
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource(HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("db.url"));
        hikariConfig.setUsername(environment.getProperty("db.user"));
        hikariConfig.setPassword(environment.getProperty("db.password"));
        hikariConfig.setDriverClassName(environment.getProperty("db.driver"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.hikari.pool-size")));
        return hikariConfig;
    }

    @Bean
    public WordsRepository wordsRepository(DataSource dataSource) {
        return new WordsRepositoryJdbcTemplateImpl(dataSource);
    }

    @Bean
    public WordCounterService wordCounterService(WordsRepository wordsRepository) {
        return new WordCounterService(wordsRepository);
    }


}
