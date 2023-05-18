package ru.tinkoff.edu.java.bot.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.DTO.LinkUpdate;
import ru.tinkoff.edu.java.bot.services.LinkService;

@RestController
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linksService) {
        this.linkService = linksService;
    }

    @PostMapping(value = "updates")
    public void sendUpdate(@RequestBody LinkUpdate request){
        linkService.updateLink(request);
    }
}
