package ru.tinkoff.edu.java.scrapper.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.Model.Chat;
import ru.tinkoff.edu.java.scrapper.repository.mapper.ChatMapper;

import java.util.List;

public class ChatJdbcRepository{

    private final JdbcTemplate jdbcTemplate;
    private final ChatMapper chatMapper;

    @Autowired
    public ChatJdbcRepository(JdbcTemplate jdbcTemplate, ChatMapper chatMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.chatMapper = chatMapper;
    }

    public List<Chat> findAll(){
        return jdbcTemplate.query("select * from chat", chatMapper);
    }

    public void add(Chat chat){jdbcTemplate.update("insert into chat (chat_id, name) values(?, ?)", chat.getChat_id(), chat.getName());
    }

    public void remove(Long id){
        jdbcTemplate.update("delete from link where link.id = ?", id);
    }
}
