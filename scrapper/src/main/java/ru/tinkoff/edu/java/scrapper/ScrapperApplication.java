package ru.tinkoff.edu.java.scrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;

/*Realization for scrapper (scrapperMain)*/

@SpringBootApplication
@EnableScheduling
public class ScrapperApplication {

    public static void main(String[] args) {

        SpringApplication.run(ScrapperApplication.class, args);
    }

    @Bean("applicationConfig")
    @ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
    public ApplicationConfig applicationConfig() {
        return new ApplicationConfig();
    }
}
