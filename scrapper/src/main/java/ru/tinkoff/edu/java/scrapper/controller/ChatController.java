package ru.tinkoff.edu.java.scrapper.controller;


import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.services.ChatService;

@RestController
@RequestMapping("/tg-chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(value = "{id}")
    public void createChat(@PathVariable Long id) {
        chatService.register(id);
    }

    @DeleteMapping(value = "{id}")
    public void deleteChat(@PathVariable Long id) {
        chatService.unregister(id);
    }
}
