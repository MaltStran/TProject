package ru.tinkoff.edu.java.bot.services;


import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.DTO.LinkUpdate;
import ru.tinkoff.edu.java.bot.exceptions.ChatNotFoundException;
import ru.tinkoff.edu.java.bot.exceptions.LinkNotFoundException;

@Service
public class LinkService {


    public void updateLink(LinkUpdate linkUpdate) {
        if(linkUpdate.id() == 1){
            throw new ChatNotFoundException("Chat not found");
        }
        if(linkUpdate.id() == 2){
            throw new LinkNotFoundException("Link not found");
        }
    }
}
