package ru.tinkoff.edu.java.scrapper.services.Jpa;

import ru.tinkoff.edu.java.scrapper.Model.Chat;
import ru.tinkoff.edu.java.scrapper.Model.Jpa.ChatJpa;
import ru.tinkoff.edu.java.scrapper.repository.Jpa.ChatJpaRepository;
import ru.tinkoff.edu.java.scrapper.services.ChatService;

public class JpaChatService implements ChatService {

    private ChatJpaRepository chatJpaRepository;

    public JpaChatService(ChatJpaRepository chatJpaRepository) {
        this.chatJpaRepository = chatJpaRepository;
    }

    @Override
    public void register(long tgChatId) {
        chatJpaRepository.save(new ChatJpa(tgChatId));
    }

    @Override
    public void unregister(long tgChatId) {
        chatJpaRepository.delete(new ChatJpa(tgChatId));
    }
}
