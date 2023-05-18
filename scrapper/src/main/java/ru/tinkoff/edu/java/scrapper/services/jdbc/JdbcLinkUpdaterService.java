package ru.tinkoff.edu.java.scrapper.services.jdbc;

import ru.tinkoff.edu.java.linkParser.ParserURL;
import ru.tinkoff.edu.java.linkParser.records.GitHubRecord;
import ru.tinkoff.edu.java.linkParser.records.Result;
import ru.tinkoff.edu.java.linkParser.records.StackOverFlowRecord;
import ru.tinkoff.edu.java.scrapper.DTO.GitHubResponse;
import ru.tinkoff.edu.java.scrapper.DTO.LinkUpdate;
import ru.tinkoff.edu.java.scrapper.DTO.StackOverflowQuestion;
import ru.tinkoff.edu.java.scrapper.Model.Chat;
import ru.tinkoff.edu.java.scrapper.Model.Link;
import ru.tinkoff.edu.java.scrapper.clients.GitHubClient;
import ru.tinkoff.edu.java.scrapper.clients.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.repository.ChatLinkJdbcRepository;
import ru.tinkoff.edu.java.scrapper.repository.LinkJdbcRepository;
import ru.tinkoff.edu.java.scrapper.services.LinkUpdater;
import ru.tinkoff.edu.java.scrapper.services.UpdateService;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.List;

public class JdbcLinkUpdaterService implements LinkUpdater {

    private Long time = 10000000L;

    private final LinkJdbcRepository linkJdbcRepository;
    private final ChatLinkJdbcRepository chatLinkJdbcRepository;
    private final GitHubClient gitHubClient;
    private final StackOverflowClient stackOverflowClient;
    private final UpdateService updateService;
    private final ParserURL parserURL;

    public JdbcLinkUpdaterService(LinkJdbcRepository linkJdbcRepository, ChatLinkJdbcRepository chatLinkJdbcRepository,
                                  GitHubClient gitHubClient, StackOverflowClient stackOverflowClient,
                                  UpdateService updateService, ParserURL parserURL) {
        this.linkJdbcRepository = linkJdbcRepository;
        this.chatLinkJdbcRepository = chatLinkJdbcRepository;
        this.gitHubClient = gitHubClient;
        this.stackOverflowClient = stackOverflowClient;
        this.updateService = updateService;
        this.parserURL = parserURL;
    }

    @Override
    public void update(){
        List<Link> links = linkJdbcRepository.findOld(time);
        for(Link link : links){
            Result result = parserURL.parse(link.getUrl());
            if(result instanceof GitHubRecord){
                GitHubResponse response = gitHubClient.fetchRepo(((GitHubRecord) result).userName(),
                        ((GitHubRecord) result).repName());
                if (response.pushedAt().toInstant().isAfter(link.getUpdate().toInstant())){
                    link.setUpdate((new Timestamp(response.pushedAt().toInstant().toEpochMilli())));
                    linkJdbcRepository.update(link);
                    List<Chat> chats = chatLinkJdbcRepository.findAllChatByLinkId(link.getId());
                    Long[] chatsId = chats.stream().map(Chat::getChat_id).toArray(Long[]::new);
                    try {
                        updateService.updateLink(new LinkUpdate(link.getId(), new URI(link.getUrl()), "pushed", chatsId));
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
                    linkJdbcRepository.update(link);
                    List<Chat> chats = chatLinkJdbcRepository.findAllChatByLinkId(link.getId());
                    Long[] chatsId = chats.stream().map(Chat::getChat_id).toArray(Long[]::new);
                    try {
                        updateService.updateLink(new LinkUpdate(link.getId(), new URI(link.getUrl()), "pushed", chatsId));
                    }
                    catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
