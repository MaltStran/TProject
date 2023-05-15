package ru.tinkoff.edu.java.scrapper.services;


import ru.tinkoff.edu.java.scrapper.Model.Link;

import java.util.List;

public interface LinkService {
    Link add(Long tgChatId, String url);
    Link remove(Long tgChatId, String url);
    List<Link> findAll(Long tgChatId);
}
