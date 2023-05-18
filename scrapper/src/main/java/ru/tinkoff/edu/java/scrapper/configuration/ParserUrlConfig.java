package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.linkParser.ParserURL;

@Configuration
public class ParserUrlConfig {

    @Bean
    public ParserURL parserURL(){
        return new ParserURL();
    }
}
