package ru.tinkoff.edu.java.scrapper.services.Jpa;

import ru.tinkoff.edu.java.linkParser.ParserURL;
import ru.tinkoff.edu.java.linkParser.records.GitHubRecord;
import ru.tinkoff.edu.java.linkParser.records.Result;
import ru.tinkoff.edu.java.linkParser.records.StackOverFlowRecord;
import ru.tinkoff.edu.java.scrapper.DTO.GitHubResponse;
import ru.tinkoff.edu.java.scrapper.DTO.LinkUpdate;
import ru.tinkoff.edu.java.scrapper.DTO.StackOverflowQuestion;
import ru.tinkoff.edu.java.scrapper.Model.Chat;
import ru.tinkoff.edu.java.scrapper.Model.Jpa.ChatJpa;
import ru.tinkoff.edu.java.scrapper.Model.Jpa.LinkJpa;
import ru.tinkoff.edu.java.scrapper.Model.Link;
import ru.tinkoff.edu.java.scrapper.clients.BotClient;
import ru.tinkoff.edu.java.scrapper.clients.GitHubClient;
import ru.tinkoff.edu.java.scrapper.clients.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.repository.ChatLinkJdbcRepository;
import ru.tinkoff.edu.java.scrapper.repository.Jpa.ChatLinkRepositoryJpa;
import ru.tinkoff.edu.java.scrapper.repository.Jpa.LinkJpaRepository;
import ru.tinkoff.edu.java.scrapper.repository.LinkJdbcRepository;
import ru.tinkoff.edu.java.scrapper.services.LinkUpdater;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.List;

public class JpaLinkUpdateService implements LinkUpdater {
    private Long time = 10000000L;
    private final LinkJpaRepository linkJpaRepository;
    private final ChatLinkRepositoryJpa chatLinkJpaRepository;
    private final GitHubClient gitHubClient;
    private final StackOverflowClient stackOverflowClient;
    private final BotClient botClient;
    private final ParserURL parserURL;

    public JpaLinkUpdateService(LinkJpaRepository linkJpaRepository, ChatLinkRepositoryJpa chatLinkJpaRepository,
                                GitHubClient gitHubClient, StackOverflowClient stackOverflowClient,
                                BotClient botClient, ParserURL parserURL) {
        this.linkJpaRepository = linkJpaRepository;
        this.chatLinkJpaRepository = chatLinkJpaRepository;
        this.gitHubClient = gitHubClient;
        this.stackOverflowClient = stackOverflowClient;
        this.botClient = botClient;
        this.parserURL = parserURL;
    }

    @Override
    public void update(){
        List<LinkJpa> links = linkJpaRepository.findOld(new Timestamp(System.currentTimeMillis() - time));
        for(LinkJpa link : links){
            Result result = parserURL.parse(link.getUrl());
            if(result instanceof GitHubRecord){
                GitHubResponse response = gitHubClient.fetchRepo(((GitHubRecord) result).userName(),
                        ((GitHubRecord) result).repName());
                if (response.pushedAt().toInstant().isAfter(link.getUpdate().toInstant())){
                    link.setUpdate((new Timestamp(response.pushedAt().toInstant().toEpochMilli())));
                    linkJpaRepository.update(link);
                    Long[] chatsId = chatLinkJpaRepository.findAllChatByLinkId(link.getId()).stream().map(ChatJpa::getChat_id).toArray(Long[]::new);
                    try {
                        botClient.updateLink(new LinkUpdate(link.getId(), new URI(link.getUrl()), "pushed", chatsId));
                    }
                    catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }else if(result instanceof StackOverFlowRecord){
                StackOverflowQuestion response = stackOverflowClient
                        .fetchQuestion(((StackOverFlowRecord) result).id()).questionList().get(0);
                if(response.lastActivityDate().toInstant().isAfter(link.getUpdate().toInstant())){
                    link.setUpdate((new Timestamp(response.lastActivityDate().toInstant().toEpochMilli())));
                    linkJpaRepository.update(link);
                    Long[] chatsId = chatLinkJpaRepository.findAllChatByLinkId(link.getId()).stream().map(ChatJpa::getChat_id).toArray(Long[]::new);
                    try {
                        botClient.updateLink(new LinkUpdate(link.getId(), new URI(link.getUrl()), "pushed", chatsId));
                    }
                    catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
