package ru.tinkoff.edu.java.scrapper.services.jdbc;

import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.Model.Chat;
import ru.tinkoff.edu.java.scrapper.repository.ChatJdbcRepository;
import ru.tinkoff.edu.java.scrapper.services.ChatService;

public class JdbcChatService implements ChatService {

    private ChatJdbcRepository chatJdbcRepository;

    public JdbcChatService(ChatJdbcRepository chatJdbcRepository) {
        this.chatJdbcRepository = chatJdbcRepository;
    }

    @Override
    public void register(long tgChatId) {
        chatJdbcRepository.add(new Chat(tgChatId));
    }

    @Override
    public void unregister(long tgChatId) {
        chatJdbcRepository.remove(tgChatId);
    }
}
