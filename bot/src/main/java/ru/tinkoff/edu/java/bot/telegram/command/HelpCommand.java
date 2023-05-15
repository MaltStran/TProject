package ru.tinkoff.edu.java.bot.telegram.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class HelpCommand implements Command{

    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return "Получить список доступных комманд";
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(),
                "Список доступных команд: \n" +
                        "/help - Список доступных команд.\n" +
                        "/track - Начать отслеживание ссылки.\n" +
                        "/untrack - Перестать отслеживать ссылку.\n" +
                        "/list - Список отслеживаемых ссылок.");
    }

}
