package ru.tinkoff.edu.java.scrapper.configuration;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.linkParser.ParserURL;
import ru.tinkoff.edu.java.scrapper.clients.BotClient;
import ru.tinkoff.edu.java.scrapper.clients.GitHubClient;
import ru.tinkoff.edu.java.scrapper.clients.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.repository.ChatJdbcRepository;
import ru.tinkoff.edu.java.scrapper.repository.ChatLinkJdbcRepository;
import ru.tinkoff.edu.java.scrapper.repository.LinkJdbcRepository;
import ru.tinkoff.edu.java.scrapper.repository.mapper.ChatLinkMapper;
import ru.tinkoff.edu.java.scrapper.repository.mapper.ChatMapper;
import ru.tinkoff.edu.java.scrapper.repository.mapper.LinkMapper;
import ru.tinkoff.edu.java.scrapper.services.jdbc.JdbcChatService;
import ru.tinkoff.edu.java.scrapper.services.jdbc.JdbcLinkService;
import ru.tinkoff.edu.java.scrapper.services.jdbc.JdbcLinkUpdaterService;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
public class JdbcConfig {

    @Bean
    public LinkMapper linkMapper() {
        return new LinkMapper();
    }

    @Bean
    public ChatMapper chatMapper() {
        return new ChatMapper();
    }

    @Bean
    public ChatLinkMapper chatLinkMapper() {
        return  new ChatLinkMapper();
    }

    @Bean
    public LinkJdbcRepository linkJdbcRepository(JdbcTemplate jdbcTemplate, LinkMapper linkMapper) {
        return new LinkJdbcRepository(jdbcTemplate, linkMapper);
    }

    @Bean
    public ChatJdbcRepository chatJdbcRepository(JdbcTemplate jdbcTemplate, ChatMapper chatMapper){
        return new ChatJdbcRepository(jdbcTemplate, chatMapper);
    }

    @Bean
    public ChatLinkJdbcRepository chatLinkJdbcRepository(JdbcTemplate jdbcTemplate, ChatLinkMapper chatLinkMapper,
                                                         ChatMapper chatMapper, LinkMapper linkMapper){
        return new ChatLinkJdbcRepository(jdbcTemplate, chatLinkMapper, chatMapper, linkMapper);
    }

    @Bean
    public JdbcChatService jdbcChatService(ChatJdbcRepository chatJdbcRepository){
        return new JdbcChatService(chatJdbcRepository);
    }

    @Bean
    public JdbcLinkService jdbcLinkService(LinkJdbcRepository linkJdbcRepository, ChatLinkJdbcRepository chatLinkRepositoryJdbc){
        return  new JdbcLinkService( linkJdbcRepository, chatLinkRepositoryJdbc);
    }

    @Bean
    public JdbcLinkUpdaterService JdbcLinkUpdateService(LinkJdbcRepository linkJdbcRepository, ChatLinkJdbcRepository chatLinkRepositoryJdbc,
                                                        GitHubClient gitHubClient, StackOverflowClient stackOverflowClient,
                                                        ParserURL parserURL, BotClient botClient){
        return  new JdbcLinkUpdaterService(linkJdbcRepository, chatLinkRepositoryJdbc, gitHubClient,
                stackOverflowClient, botClient, parserURL);
    }
}
