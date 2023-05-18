package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {
    @Bean
    public long schedulerTimer(ApplicationConfig config) {
        return config.scheduler().interval().toMillis();
    }
}
