package ru.tinkoff.edu.java.scrapper.configuration;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.services.ScrapperQueueProducer;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "true")
public class RabbitMQConfiguration {
    private final String queueName;
    private final String exchangeName;

    public RabbitMQConfiguration(ApplicationConfig config) {
        this.queueName = config.queueName();
        this.exchangeName = config.exchangeName();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(queueName).withArgument("x-dead-letter-exchange", exchangeName + ".dlq").build();
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).withQueueName();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ScrapperQueueProducer scrapperQueueProducer(Queue queue){
        return new ScrapperQueueProducer(new RabbitTemplate(), queue);
    }
}
