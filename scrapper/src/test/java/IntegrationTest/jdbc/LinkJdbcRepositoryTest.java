package IntegrationTest.jdbc;

import IntegrationTest.IntegrationEnvironment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.Model.Link;
import ru.tinkoff.edu.java.scrapper.repository.LinkJdbcRepository;
import ru.tinkoff.edu.java.scrapper.repository.mapper.LinkMapper;

import java.sql.Timestamp;
import java.util.List;

public class LinkJdbcRepositoryTest extends IntegrationEnvironment {

    @Autowired
    private LinkMapper linkMapper;

    @Autowired
    private LinkJdbcRepository linkJdbcRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Transactional
    @Rollback
    public void addTest(){
        Link link = new Link("https://stackoverflow.com/questions/69218585/what-are-sealed-classes-in-java-17",
                new Timestamp(System.currentTimeMillis()));
        linkJdbcRepository.add(link);
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
        Link link1 = linkJdbcRepository.findByUrl(link.getUrl());
        Assertions.assertEquals(link.getUpdate(), link1.getUpdate());
        Assertions.assertEquals(link.getUrl(), link1.getUrl());
    }

    @Test
    @Transactional
    @Rollback
    public void findAllTest(){
        Link link = new Link("https://stackoverflow.com/questions/69218585/what-are-sealed-classes-in-java-17",
                new Timestamp(System.currentTimeMillis()));
        List<Link> links = linkJdbcRepository.findAll();
        jdbcTemplate.update("insert into link (url, update) values (?,?)", link.getUrl(), link.getUpdate());
        List<Link> links1 = linkJdbcRepository.findAll();
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
        linkJdbcRepository.remove(links.get(0).getId());
        List<Link> links1 = jdbcTemplate.query("select * from link", linkMapper);
        Assertions.assertEquals(links.size(), 1);
        Assertions.assertEquals(links1.size(), 0);
    }
}
