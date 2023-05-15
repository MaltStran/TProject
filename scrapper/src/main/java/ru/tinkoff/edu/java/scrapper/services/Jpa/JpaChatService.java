package ru.tinkoff.edu.java.scrapper.services.Jpa;

import ru.tinkoff.edu.java.scrapper.Model.Chat;
import ru.tinkoff.edu.java.scrapper.repository.Jpa.ChatJpaRepository;
import ru.tinkoff.edu.java.scrapper.services.ChatService;

public class JpaChatService implements ChatService {

    private ChatJpaRepository chatJpaRepository;

    public JpaChatService(ChatJpaRepository chatJpaRepository) {
        this.chatJpaRepository = chatJpaRepository;
    }

    @Override
    public void register(long tgChatId) {
        chatJpaRepository.add(new Chat(tgChatId));
    }

    @Override
    public void unregister(long tgChatId) {
        chatJpaRepository.remove(tgChatId);
    }
}
