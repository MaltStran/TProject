package commands;


import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tinkoff.edu.java.bot.telegram.command.TrackCommand;
import ru.tinkoff.edu.java.bot.telegram.processor.Processor;
import ru.tinkoff.edu.java.bot.telegram.processor.UserMessageProcessor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UnSupCommand {

    @Mock
    private Update mockUpdate;

    @Mock
    private Message mockMessage;

    @Mock
    private TrackCommand trackCommand;

    @Mock
    private Chat mockChat;


    private Long id = 1L;

    @Test
    public void afterTrack(){
        when(mockUpdate.message()).thenReturn(mockMessage);
        when(mockUpdate.message().text()).thenReturn("/track");
        when(trackCommand.command()).thenReturn("/track");
        UserMessageProcessor proc = new Processor(trackCommand);
        SendMessage resultBefore = proc.process(mockUpdate);
        when(mockUpdate.message().text()).thenReturn("https://github.com/sanyarnd/tinkoff-java-course-2022/");
        SendMessage resultAfter = proc.process(mockUpdate);
        assertAll(() -> assertNull(resultBefore));
        assertAll(() -> assertNull(resultAfter));
    }

    @Test
    public void unSupCommand(){
        when(mockUpdate.message()).thenReturn(mockMessage);
        when(mockMessage.chat()).thenReturn(mockChat);
        when(mockMessage.chat().id()).thenReturn(id);
        when(mockUpdate.message().text()).thenReturn("123");
        when(trackCommand.command()).thenReturn("/track");
        UserMessageProcessor proc = new Processor(trackCommand);
        SendMessage result = proc.process(mockUpdate);
        assertAll(() -> assertEquals(result.getParameters().get("text"), "Команда не поддерживается, попробуйте /help"));
    }
}
