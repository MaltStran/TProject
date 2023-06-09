package ru.tinkoff.edu.java.scrapper.service.jpa;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.domain.repository.jpa.ChatLinkEntityRepository;
import ru.tinkoff.edu.java.scrapper.domain.repository.jpa.LinkEntityRepository;
import ru.tinkoff.edu.java.scrapper.domain.repository.jpa.TgChatEntityRepository;
import ru.tinkoff.edu.java.scrapper.domain.repository.jpa.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.domain.repository.jpa.entity.TgChatEntity;
import ru.tinkoff.edu.java.scrapper.excontroller.exception.NotFoundException;
import ru.tinkoff.edu.java.scrapper.service.interfaces.TgChatService;

@AllArgsConstructor
public class JpaTgChatService implements TgChatService {
    private final TgChatEntityRepository tgChatEntityRepository;
    private final LinkEntityRepository linkEntityRepository;
    private final ChatLinkEntityRepository chatLinkEntityRepository;

    private void untrackLink(LinkEntity link, TgChatEntity tgChat) {
        chatLinkEntityRepository.deleteByTgChatAndLink(tgChat, link);
        if (chatLinkEntityRepository.getTgChatsByLinkId(link.getId()).size() == 0) {
            linkEntityRepository.deleteById(link.getId());
        }
    }

    @Transactional
    @Override
    public void unregister(Long tgChatId) {
        TgChatEntity tgChatEntity = tgChatEntityRepository.findByTgChatId(tgChatId)
            .orElseThrow(() -> new NotFoundException("Чат '" + tgChatId + "' не найден"));
        List<LinkEntity> trackedLinks = chatLinkEntityRepository.getLinksByTgChatId(tgChatId);
        for (LinkEntity link: trackedLinks) {
            untrackLink(link, tgChatEntity);
        }
        tgChatEntityRepository.deleteById(tgChatEntity.getId());
    }

    @Transactional
    @Override
    public void register(Long tgChatId) {
        tgChatEntityRepository.insertTgChat(tgChatId);
    }
}
