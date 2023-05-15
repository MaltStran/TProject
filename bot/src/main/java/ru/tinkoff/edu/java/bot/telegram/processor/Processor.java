package ru.tinkoff.edu.java.bot.telegram.processor;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.telegram.command.Command;
import ru.tinkoff.edu.java.bot.telegram.command.HelpCommand;
import ru.tinkoff.edu.java.bot.telegram.command.StartCommand;

import java.util.Arrays;
import java.util.List;

@Component
public class Processor implements UserMessageProcessor{

    private Command lastCommand = new HelpCommand();

    private final List<? extends Command> commands;

    @Autowired
    public Processor(Command... commands) {
        this.commands = Arrays.stream(commands).toList();
    }

    @Override
    public List<? extends Command> commands() {
        return commands;
    }

    @Override
    public SendMessage process(Update update){
        for (Command command : commands) {
            if (command.command().equals(update.message().text())) {
                lastCommand = command;
                return command.handle(update);
            }
        }
        if(lastCommand.command().equals("/track") || lastCommand.command().equals("/untrack")) {
            return lastCommand.handle(update);
        }
        return unsupportedCommand(update);
    }


    private SendMessage unsupportedCommand(Update update) {
        return new SendMessage(update.message().chat().id(), "Команда не поддерживается, попробуйте /help");
    }
}
