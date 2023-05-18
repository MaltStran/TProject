package commands;


import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tinkoff.edu.java.bot.DTO.LinkResponse;
import ru.tinkoff.edu.java.bot.DTO.ListLinkResponse;
import ru.tinkoff.edu.java.bot.Model.Link;
import ru.tinkoff.edu.java.bot.clients.LinkClient;
import ru.tinkoff.edu.java.bot.telegram.command.ListCommand;


import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListTest {

    @Mock
    private LinkClient client = Mockito.mock(LinkClient.class);

    @Mock
    private Update mockUpdate;

    @Mock
    private Message mockMessage;

    @Mock
    private Chat mockChat;

    private Long id = 1L;

    @Test
    public void noLinks(){
        when(mockUpdate.message()).thenReturn(mockMessage);
        when(mockMessage.chat()).thenReturn(mockChat);
        when(mockMessage.chat().id()).thenReturn(id);
        ListCommand listCommand = new ListCommand(client);
        SendMessage result = listCommand.handle(mockUpdate);
        assertAll(() -> assertEquals(result.getParameters().get("text"), "Нет отслеживаемых ссылок"));
    }


    @Test
    public void existLinks(){
        when(mockUpdate.message()).thenReturn(mockMessage);
        when(mockMessage.chat()).thenReturn(mockChat);
        when(mockMessage.chat().id()).thenReturn(id);
        when(client.getAllLinks(id)).thenReturn(new ListLinkResponse(List.of(
                new LinkResponse(id, URI.create("https://github.com/sanyarnd/tinkoff-java-course-2022/")),
                new LinkResponse(id, URI.create("https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c"))), 2));
        ListCommand listCommand = new ListCommand(client);
        SendMessage result = listCommand.handle(mockUpdate);
        String res = """
                Список отслеживаемых ссылок:
                https://github.com/sanyarnd/tinkoff-java-course-2022/
                https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c
                """;
        assertAll(() -> assertEquals(result.getParameters().get("text"), res));
    }
}
