package ru.tinkoff.edu.java.bot.services;


import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.DTO.LinkUpdate;
import ru.tinkoff.edu.java.bot.exceptions.ChatNotFoundException;
import ru.tinkoff.edu.java.bot.exceptions.LinkNotFoundException;
import ru.tinkoff.edu.java.bot.telegram.bot.Bot;

@Service
public class LinkService implements UpdateService{

    private final Bot bot;

    public LinkService(Bot bot) {
        this.bot = bot;
    }


    public void updateLink(LinkUpdate linkUpdate) {
        for(Long id: linkUpdate.tgChatIds()){
            bot.send(id, linkUpdate.url() + linkUpdate.description());
        }
    }
}
