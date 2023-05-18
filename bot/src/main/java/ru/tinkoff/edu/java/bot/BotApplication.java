package ru.tinkoff.edu.java.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;


//MaltStranTgBot

@Slf4j
@SpringBootApplication
public class BotApplication {

    public static void main(String[] args) {
        var ctx = SpringApplication.run(BotApplication.class, args);
        ApplicationConfig conf = ctx.getBean(ApplicationConfig.class);
        log.info(conf.toString());

    }
}
