package ru.tinkoff.edu.java.scrapper.repository.mapper;


import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.Model.Link;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LinkMapper implements RowMapper<Link> {

    @Override
    public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Link(rs.getLong("id"),
                        rs.getString("url"),
                        rs.getTimestamp("update"));
    }
}
