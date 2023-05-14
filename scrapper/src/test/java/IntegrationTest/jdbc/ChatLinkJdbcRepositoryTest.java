package IntegrationTest.jdbc;

import IntegrationTest.IntegrationEnvironment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.Model.ChatLink;
import ru.tinkoff.edu.java.scrapper.Model.Link;
import ru.tinkoff.edu.java.scrapper.repository.ChatLinkJdbcRepository;
import ru.tinkoff.edu.java.scrapper.repository.mapper.ChatLinkMapper;

import java.sql.Timestamp;
import java.util.List;

public class ChatLinkJdbcRepositoryTest extends IntegrationEnvironment {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ChatLinkMapper chatLinkMapper;

    @Autowired
    private ChatLinkJdbcRepository chatLinkJdbcRepository;


    @Test
    @Transactional
    @Rollback
    public void addTest(){
        ChatLink chatLink = new ChatLink(1L, 1L);
        chatLinkJdbcRepository.add(chatLink);
        List<ChatLink> chatLinks = jdbcTemplate.query("select * from chat_link", chatLinkMapper);
        Assertions.assertEquals(chatLink, chatLinks.get(0));
    }


    @Test
    @Transactional
    @Rollback
    public void removeTest(){
        ChatLink chatLink = new ChatLink(1L, 1L);
        jdbcTemplate.update("insert into chat_link (chat_id, link_id) VALUES (?,?)",
                chatLink.getChat_id(), chatLink.getLink_id());
        List<ChatLink> chatLinks = jdbcTemplate.query("select * from chat_link", chatLinkMapper);
        chatLinkJdbcRepository.remove(chatLink);
        List<ChatLink> chatLinks1 = jdbcTemplate.query("select * from chat_link", chatLinkMapper);
        Assertions.assertEquals(chatLinks.size(), 1);
        Assertions.assertEquals(chatLinks1.size(), 0);
    }

    @Test
    @Transactional
    @Rollback
    public void findAllTest(){
        ChatLink chatLink = new ChatLink(1L, 1L);
        jdbcTemplate.update("insert into chat_link (chat_id, link_id) VALUES (?,?)",
                chatLink.getChat_id(), chatLink.getLink_id());
        List<ChatLink> chatLinks = chatLinkJdbcRepository.findAll();
        Assertions.assertEquals(chatLink, chatLinks.get(0));
    }
}
