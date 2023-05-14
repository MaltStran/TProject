package ru.tinkoff.edu.java.scrapper.controller;


import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.DTO.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.DTO.LinkResponse;
import ru.tinkoff.edu.java.scrapper.DTO.ListLinkResponse;
import ru.tinkoff.edu.java.scrapper.DTO.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.Model.Link;
import ru.tinkoff.edu.java.scrapper.services.LinkService;

import java.util.List;

@RestController
@RequestMapping("/links")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping
    public ListLinkResponse getLinks(@RequestHeader("Tg-Chat-Id") Long chatId) {
        List<Link> list = linkService.findAll(chatId);
        return new ListLinkResponse(list, list.size());
    }

    @PostMapping
    public LinkResponse addLink(@RequestHeader("Tg-Chat-Id") Long chatId, @RequestBody AddLinkRequest request) {
        Link link = linkService.add(chatId, request.url());
        return new LinkResponse(link.getId(), link.getUrl());
    }

    @DeleteMapping
    public LinkResponse deleteLink(@RequestHeader("Tg-Chat-Id") Long chatId, @RequestBody RemoveLinkRequest request) {
        Link link = linkService.remove(chatId, request.link());
        return new LinkResponse(link.getId(), link.getUrl());
    }

}
