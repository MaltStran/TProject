package ru.tinkoff.edu.java.scrapper.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.Model.Chat;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ChatMapper implements RowMapper<Chat> {
    @Override
    public Chat mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Chat(rs.getLong("id"), rs.getLong("chat_id"), rs.getString("name"));
    }
}
