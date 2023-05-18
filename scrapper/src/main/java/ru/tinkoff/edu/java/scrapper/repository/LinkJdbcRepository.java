package ru.tinkoff.edu.java.scrapper.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.Model.Link;
import ru.tinkoff.edu.java.scrapper.repository.mapper.LinkMapper;

import java.sql.Timestamp;
import java.util.List;

public class LinkJdbcRepository {

    private final JdbcTemplate jdbcTemplate;
    private final LinkMapper linkMapper;

    public LinkJdbcRepository(JdbcTemplate jdbcTemplate, LinkMapper linkMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.linkMapper = linkMapper;
    }

    public List<Link> findAll() {
        return jdbcTemplate.query("select * from link", linkMapper);
    }

    public Link findByUrl(String url) {
        List<Link> links = jdbcTemplate.query("select * from link where url = ?", linkMapper, url);
        return links.size() == 0 ? null : links.get(0);
    }

    public void add(Link link) {
        jdbcTemplate.update("insert into link (url, update) values(?, ?)",
                link.getUrl(), link.getUpdate());
    }

    public void remove(Long id) {
        jdbcTemplate.update("delete from link where link.id = ?", id);
    }

    public List<Link> findOld(long time){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() - time);
        return jdbcTemplate.query("select * from link where link.update < ?", linkMapper, timestamp);
    }

    public void update(Link link){
        jdbcTemplate.update("update link set update = ? where id = ?",
                link.getUpdate(), link.getId());
    }
}
