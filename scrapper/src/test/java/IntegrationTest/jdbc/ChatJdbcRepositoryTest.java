package IntegrationTest.jdbc;

import IntegrationTest.IntegrationEnvironment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.Model.Chat;
import ru.tinkoff.edu.java.scrapper.repository.ChatJdbcRepository;
import ru.tinkoff.edu.java.scrapper.repository.mapper.ChatMapper;

import java.util.List;

public class ChatJdbcRepositoryTest extends IntegrationEnvironment {

    @Autowired
    private ChatJdbcRepository chatJdbcRepository;

    @Autowired
    private ChatMapper chatMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Transactional
    @Rollback
    public void addTest(){
        Chat chat = new Chat(1L, "name");
        chatJdbcRepository.add(chat);
        List<Chat> chats = jdbcTemplate.query("select * from chat", chatMapper);
        Assertions.assertEquals(chat.getChat_id(), chats.get(0).getChat_id());
        Assertions.assertEquals(chat.getName(), chats.get(0).getName());
    }

    @Test
    @Transactional
    @Rollback
    public void findAllTest(){
        Chat chat = new Chat(1L, "name");
        jdbcTemplate.update("insert into chat (chat_id, name) values (?, ?)", chat.getChat_id(), chat.getName());
        List<Chat> chats = chatJdbcRepository.findAll();
        Assertions.assertEquals(chats.size(), 1);
    }

    @Test
    @Transactional
    @Rollback
    public void removeTest(){
        Chat chat = new Chat(1L, "name");
        jdbcTemplate.update("insert into chat (chat_id, name) values (?, ?)", chat.getChat_id(), chat.getName());
        List<Chat> chats1 = jdbcTemplate.query("select * from chat", chatMapper);
        chatJdbcRepository.remove(chats1.get(0).getId());
        List<Chat> chats2 = jdbcTemplate.query("select * from chat", chatMapper);
        Assertions.assertEquals(chats1.size(), 1);
        Assertions.assertEquals(chats2.size(), 0);
    }
}
