package ru.tinkoff.edu.java.bot.telegram.command;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import java.net.URISyntaxException;

public interface Command {
    String command();

    String description();

    SendMessage handle(Update update);

    default boolean supports(Update update) {
        return false;
    }

    default BotCommand toApiCommand() {
        return new BotCommand(command(), description());
    }
}
