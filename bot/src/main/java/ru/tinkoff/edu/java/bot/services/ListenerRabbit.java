package ru.tinkoff.edu.java.bot.services;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.DTO.LinkUpdate;

@Service
@RabbitListener(queues = "${app.queue-name}")
public class ListenerRabbit {

    private final UpdateService updateService;


    public ListenerRabbit(UpdateService updateService) {
        this.updateService = updateService;
    }

    @RabbitHandler
    public void update(LinkUpdate linkUpdate){
        updateService.updateLink(linkUpdate);
    }
}
