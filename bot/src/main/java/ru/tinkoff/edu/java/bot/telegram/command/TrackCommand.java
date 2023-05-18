package ru.tinkoff.edu.java.bot.telegram.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.DTO.AddLinkRequest;
import ru.tinkoff.edu.java.bot.clients.LinkClient;

@Component
public class TrackCommand implements Command{
    private final LinkClient linkClient;

    public TrackCommand(LinkClient linkClient) {
        this.linkClient = linkClient;
    }

    @Override
    public String command() {
        return "/track";
    }

    @Override
    public String description() {
        return "Начать отслеживать ссылку.";
    }

    @Override
    public SendMessage handle(Update update) {
        String message = update.message().text();
        if (message.charAt(0) == '/') return new SendMessage(update.message().chat().id(), "Введите ссылку");
        linkClient.addLink(update.message().chat().id(), new AddLinkRequest(update.message().text()));
        return new SendMessage(update.message().chat().id(), "Ссылка отслеживается");
    }
}
