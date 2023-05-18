package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.clients.ChatClient;
import ru.tinkoff.edu.java.bot.clients.LinkClient;

@Configuration
public class ClientConfig {
    @Bean
    public LinkClient linkClient() {
        return new LinkClient();
    }

    @Bean
    public ChatClient stackOverflowClientService() {
        return new ChatClient();
    }
}
