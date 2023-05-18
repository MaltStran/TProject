package ru.tinkoff.edu.java.bot.telegram.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.clients.ChatClient;

@Component
public class StartCommand implements Command{
    private final ChatClient chatClient;

    public StartCommand(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return "Старт бота";
    }

    @Override
    public SendMessage handle(Update update) {
        chatClient.registerChat(update.message().chat().id());
        return new SendMessage(update.message().chat().id(), "Введите команду /help");
    }
}
