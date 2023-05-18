package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.linkParser.ParserURL;
import ru.tinkoff.edu.java.scrapper.clients.BotClient;
import ru.tinkoff.edu.java.scrapper.clients.GitHubClient;
import ru.tinkoff.edu.java.scrapper.clients.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.repository.Jpa.ChatJpaRepository;
import ru.tinkoff.edu.java.scrapper.repository.Jpa.ChatLinkRepositoryJpa;
import ru.tinkoff.edu.java.scrapper.repository.Jpa.LinkJpaRepository;
import ru.tinkoff.edu.java.scrapper.services.Jpa.JpaChatService;
import ru.tinkoff.edu.java.scrapper.services.Jpa.JpaLinkService;
import ru.tinkoff.edu.java.scrapper.services.Jpa.JpaLinkUpdateService;
import ru.tinkoff.edu.java.scrapper.services.UpdateService;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaConfig {

    @Bean
    public JpaChatService jpaChatService(ChatJpaRepository chatJpaRepository){
        return new JpaChatService(chatJpaRepository);
    }

    @Bean
    public JpaLinkService jpaLinkService(LinkJpaRepository linkJpaRepository, ChatLinkRepositoryJpa chatLinkRepositoryJpa){
        return  new JpaLinkService(chatLinkRepositoryJpa, linkJpaRepository);
    }

    @Bean
    public JpaLinkUpdateService jpaLinkUpdateService(LinkJpaRepository linkJpaRepository, ChatLinkRepositoryJpa chatLinkRepositoryJpa,
                                                     GitHubClient gitHubClient, StackOverflowClient stackOverflowClient,
                                                     ParserURL parserURL, UpdateService updateService){
        return  new JpaLinkUpdateService(linkJpaRepository, chatLinkRepositoryJpa, gitHubClient,
                stackOverflowClient, updateService, parserURL);
    }
}
