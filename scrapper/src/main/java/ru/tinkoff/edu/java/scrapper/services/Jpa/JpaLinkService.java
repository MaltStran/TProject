package ru.tinkoff.edu.java.scrapper.services.Jpa;

import ru.tinkoff.edu.java.scrapper.Model.ChatLink;
import ru.tinkoff.edu.java.scrapper.Model.Jpa.LinkJpa;
import ru.tinkoff.edu.java.scrapper.Model.Link;
import ru.tinkoff.edu.java.scrapper.repository.Jpa.ChatLinkRepositoryJpa;
import ru.tinkoff.edu.java.scrapper.repository.Jpa.LinkJpaRepository;
import ru.tinkoff.edu.java.scrapper.services.LinkService;

import java.sql.Timestamp;
import java.util.List;

public class JpaLinkService implements LinkService {

    private ChatLinkRepositoryJpa chatLinkRepository;
    private LinkJpaRepository linkJpaRepository;

    public JpaLinkService(ChatLinkRepositoryJpa chatLinkRepository, LinkJpaRepository linkJpaRepository) {
        this.chatLinkRepository = chatLinkRepository;
        this.linkJpaRepository = linkJpaRepository;
    }

    @Override
    public Link add(Long tgChatId, String url) {
        linkJpaRepository.add(new Link(url, new Timestamp(System.currentTimeMillis())));
        LinkJpa link = linkJpaRepository.findByUrl(url);
        chatLinkRepository.add(new ChatLink(tgChatId, link.getId()));
        return new Link(link);
    }

    @Override
    public Link remove(Long tgChatId, String url) {
        LinkJpa link = linkJpaRepository.findByUrl(url);
        chatLinkRepository.remove(new ChatLink(tgChatId, link.getId()));
        return new Link(link);
    }

    @Override
    public List<Link> findAll(Long tgChatId) {
        return chatLinkRepository.findAllLinkByChatId(tgChatId).stream().map(link -> new Link(link)).toList();

    }
}
