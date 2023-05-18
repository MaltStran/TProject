package ru.tinkoff.edu.java.scrapper.service.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.model.bot.LinkUpdateRequest;
import ru.tinkoff.edu.java.scrapper.service.UpdatesSender;

@Slf4j
public class ScrapperQueueProducer implements UpdatesSender {
    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    public ScrapperQueueProducer(RabbitTemplate rabbitTemplate, ApplicationConfig conf) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = conf.getRabbitQueue().getExchangeName();
        this.routingKey = conf.getRabbitQueue().getRoutingKey();
    }

    public void sendUpdate(LinkUpdateRequest update) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, update);
    }
}
