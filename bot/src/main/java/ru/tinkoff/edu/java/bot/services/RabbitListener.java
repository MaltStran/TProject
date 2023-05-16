package ru.tinkoff.edu.java.bot.services;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.DTO.LinkUpdate;

@Service
@RabbitListener(queues = "${app.queue-name}")
public class RabbitListener {

    private final UpdateService updateService;


    public RabbitListener(UpdateService updateService) {
        this.updateService = updateService;
    }

    @RabbitHandler
    public void update(LinkUpdate linkUpdate){
        updateService.updateLink(linkUpdate);
    }
}
