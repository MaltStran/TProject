package ru.tinkoff.edu.java.bot.telegram.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.BaseResponse;
import ru.tinkoff.edu.java.bot.telegram.command.Command;
import ru.tinkoff.edu.java.bot.telegram.processor.UserMessageProcessor;

import java.util.List;

public class BotTelegram implements Bot{
    private final TelegramBot bot;

    private final UserMessageProcessor userMessageProcessor;


    public BotTelegram(String token, UserMessageProcessor userMessageProcessor) {
        this.userMessageProcessor = userMessageProcessor;
        bot = new TelegramBot(token);
        bot.setUpdatesListener(this);
        bot.execute(new SetMyCommands(userMessageProcessor
                .commands()
                .stream()
                .map(Command::toApiCommand)
                .toArray(BotCommand[]::new)));
    }

    @Override
    public <T extends BaseRequest<T, R>, R extends BaseResponse> void execute(BaseRequest<T, R> request) {
        bot.execute(request);
    }

    @Override
    public int process(List<Update> updates) {
        for (var update : updates) {
            bot.execute(userMessageProcessor.process(update));
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public void sendMessage(Long chatId, String msg) {
        bot.execute(new SendMessage(chatId, msg));
    }

    @Override
    public void start() {
        bot.setUpdatesListener(this);
    }

    @Override
    public void send(Long id, String message){
        bot.execute(new SendMessage(id, message));
    }

    @Override
    public void close() {
        bot.removeGetUpdatesListener();
    }
}
