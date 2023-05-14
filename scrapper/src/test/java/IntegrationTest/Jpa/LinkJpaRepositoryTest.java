package IntegrationTest.Jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.Model.Jpa.LinkJpa;
import ru.tinkoff.edu.java.scrapper.Model.Link;
import ru.tinkoff.edu.java.scrapper.repository.Jpa.LinkJpaRepository;
import ru.tinkoff.edu.java.scrapper.repository.mapper.LinkMapper;

import java.sql.Timestamp;
import java.util.List;

public class LinkJpaRepositoryTest {
    @Autowired
    private LinkMapper linkMapper;

    @Autowired
    private LinkJpaRepository linkJpaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Transactional
    @Rollback
    public void addTest(){
        Link link = new Link("https://stackoverflow.com/questions/69218585/what-are-sealed-classes-in-java-17",
                new Timestamp(System.currentTimeMillis()));
        linkJpaRepository.add(link);
        List<Link> links = jdbcTemplate.query("select * from link", linkMapper);
        Assertions.assertEquals(link.getUpdate(), links.get(0).getUpdate());
        Assertions.assertEquals(link.getUrl(), links.get(0).getUrl());
    }

    @Test
    @Transactional
    @Rollback
    public void findByUrlTest(){
        Link link = new Link("https://stackoverflow.com/questions/69218585/what-are-sealed-classes-in-java-17",
                new Timestamp(System.currentTimeMillis()));
        jdbcTemplate.update("insert into link (url, update) values (?,?)", link.getUrl(), link.getUpdate());
        LinkJpa link1 = linkJpaRepository.findByUrl(link.getUrl());
        Assertions.assertEquals(link.getUpdate(), link1.getUpdate());
        Assertions.assertEquals(link.getUrl(), link1.getUrl());
    }

    @Test
    @Transactional
    @Rollback
    public void findAllTest(){
        Link link = new Link("https://stackoverflow.com/questions/69218585/what-are-sealed-classes-in-java-17",
                new Timestamp(System.currentTimeMillis()));
        List<Link> links = linkJpaRepository.findAll();
        jdbcTemplate.update("insert into link (url, update) values (?,?)", link.getUrl(), link.getUpdate());
        List<Link> links1 = linkJpaRepository.findAll();
        Assertions.assertEquals(links1.size(), 1);
        Assertions.assertEquals(links.size(), 0);
    }

    @Test
    @Transactional
    @Rollback
    public void removeTest(){
        Link link = new Link("https://stackoverflow.com/questions/69218585/what-are-sealed-classes-in-java-17",
                new Timestamp(System.currentTimeMillis()));
        jdbcTemplate.update("insert into link (url, update) values (?,?)", link.getUrl(), link.getUpdate());
        List<Link> links = jdbcTemplate.query("select * from link", linkMapper);
        linkJpaRepository.remove(links.get(0).getId());
        List<Link> links1 = jdbcTemplate.query("select * from link", linkMapper);
        Assertions.assertEquals(links.size(), 1);
        Assertions.assertEquals(links1.size(), 0);
    }
}
