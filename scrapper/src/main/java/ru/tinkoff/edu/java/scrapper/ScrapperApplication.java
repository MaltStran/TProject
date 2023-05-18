package ru.tinkoff.edu.java.scrapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;

@Slf4j
@SpringBootApplication
public class ScrapperApplication {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(ScrapperApplication.class, args);
        ApplicationConfig conf = ctx.getBean(ApplicationConfig.class);
        log.info(conf.toString());
    }
}
