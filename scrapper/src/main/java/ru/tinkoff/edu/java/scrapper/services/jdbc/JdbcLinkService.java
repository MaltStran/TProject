package ru.tinkoff.edu.java.scrapper.services.jdbc;

import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.Model.ChatLink;
import ru.tinkoff.edu.java.scrapper.Model.Link;
import ru.tinkoff.edu.java.scrapper.repository.ChatLinkJdbcRepository;
import ru.tinkoff.edu.java.scrapper.repository.LinkJdbcRepository;
import ru.tinkoff.edu.java.scrapper.services.LinkService;

import java.net.URI;
import java.sql.Timestamp;
import java.util.List;

public class JdbcLinkService implements LinkService {

    private final LinkJdbcRepository linkJdbcRepository;

    private final ChatLinkJdbcRepository chatLinkJdbcRepository;

    public JdbcLinkService(LinkJdbcRepository linkJdbcRepository, ChatLinkJdbcRepository chatLinkJdbcRepository) {
        this.linkJdbcRepository = linkJdbcRepository;
        this.chatLinkJdbcRepository = chatLinkJdbcRepository;
    }

    @Override
    public Link add(Long tgChatId, String url) {
        linkJdbcRepository.add(new Link(url, new Timestamp(System.currentTimeMillis())));
        Link link = linkJdbcRepository.findByUrl(url);
        chatLinkJdbcRepository.add(new ChatLink(tgChatId, link.getId()));
        return link;
    }

    @Override
    public Link remove(Long tgChatId, String url) {
        Link link = linkJdbcRepository.findByUrl(url);
        chatLinkJdbcRepository.remove(new ChatLink(tgChatId, link.getId()));
        return link;
    }

    @Override
    public List<Link> findAll(Long tgChatId) {
        return chatLinkJdbcRepository.findAllLinkByChatId(tgChatId);

    }
}
