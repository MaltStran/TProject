package ru.tinkoff.edu.java.scrapper.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.Model.Chat;
import ru.tinkoff.edu.java.scrapper.Model.ChatLink;
import ru.tinkoff.edu.java.scrapper.Model.Link;
import ru.tinkoff.edu.java.scrapper.repository.mapper.ChatLinkMapper;
import ru.tinkoff.edu.java.scrapper.repository.mapper.ChatMapper;
import ru.tinkoff.edu.java.scrapper.repository.mapper.LinkMapper;

import java.util.List;

public class ChatLinkJdbcRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ChatLinkMapper chatLinkMapper;
    private final ChatMapper chatMapper;
    private final LinkMapper linkMapper;

    @Autowired
    public ChatLinkJdbcRepository(JdbcTemplate jdbcTemplate, ChatLinkMapper chatLinkMapper, ChatMapper chatMapper,
                                  LinkMapper linkMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.chatLinkMapper = chatLinkMapper;
        this.chatMapper = chatMapper;
        this.linkMapper = linkMapper;
    }

    public List<Chat> findAllChatByLinkId(Long id){
        return jdbcTemplate.query("select * from chat_link inner join chat on chat_link.chat_id = chat.id" +
                " where chat_link.link_id = ?", chatMapper, id);
    }

    public List<Link> findAllLinkByChatId(Long id){
        return jdbcTemplate.query("select * from chat_link inner join link on chat_link.link_id = link.id" +
                " where chat_link.link_id = ?", linkMapper, id);
    }

    public List<ChatLink> findAll(){
        return jdbcTemplate.query("select * from chat_link", chatLinkMapper);
    }

    public void add(ChatLink chatLink){
        jdbcTemplate.update("insert into chat_link (chat_id, link_id) values (?,?)",
                chatLink.getChat_id(), chatLink.getLink_id());
    }

    public void removeByChatId(Long id){
        jdbcTemplate.update("delete from chat_link where chat_id = ?", id);
    }

    public void removeByLinkId(Long id){
        jdbcTemplate.update("delete from chat_link where link_id = ?", id);
    }

    public void remove(ChatLink chatLink){
        jdbcTemplate.update("delete from chat_link where link_id = ? and chat_id = ?",
                chatLink.getLink_id(), chatLink.getChat_id());
    }
}
