package ru.tinkoff.edu.java.bot.telegram.command;


import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.DTO.ListLinkResponse;
import ru.tinkoff.edu.java.bot.clients.LinkClient;

@Component
public class ListCommand implements Command{
    private final LinkClient linkClient;

    public ListCommand(LinkClient linkClient) {
        this.linkClient = linkClient;
    }


    @Override
    public String command() {
        return "/list";
    }

    @Override
    public String description() {
        return "Получить список отслеживаемых ссылок";
    }



    @Override
    public SendMessage handle(Update update) {
        ListLinkResponse listLinkResponse = linkClient.getAllLinks(update.message().chat().id());
        if (listLinkResponse == null || listLinkResponse.size() == 0) return new SendMessage(update.message().chat().id(), "Нет отслеживаемых ссылок");
        var answer = new StringBuilder();
        answer.append("Список отслеживаемых ссылок:\n");
        for (int i = 0; i < listLinkResponse.size(); i++) {
            answer.append(listLinkResponse.links().get(i).getUrl()).append("\n");
        }
        return new SendMessage(update.message().chat().id(), answer.toString());
    }
}
