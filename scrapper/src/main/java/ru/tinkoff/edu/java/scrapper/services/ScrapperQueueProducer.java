package ru.tinkoff.edu.java.scrapper.services;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import ru.tinkoff.edu.java.scrapper.DTO.LinkUpdate;

public class ScrapperQueueProducer implements UpdateService{

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public ScrapperQueueProducer(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    @Override
    public void updateLink(LinkUpdate linkUpdate) {
        rabbitTemplate.convertAndSend(queue.getName(), linkUpdate);
    }
}
