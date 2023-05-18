package ru.tinkoff.edu.java.bot.configuration;


import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.DTO.LinkUpdate;
import ru.tinkoff.edu.java.bot.services.ListenerRabbit;
import ru.tinkoff.edu.java.bot.services.UpdateService;

import java.util.HashMap;
import java.util.Map;

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
    public FanoutExchange notFoundExchange() {
        return new FanoutExchange(queueName + ".dlq", true, false);
    }

    @Bean
    public Queue notFoundQueue() {
        return QueueBuilder.durable(queueName + ".dlq").build();
    }

    @Bean
    public Binding notFoundBinding(Queue notFoundQueue, FanoutExchange notFoundExchange) {
        return BindingBuilder
                .bind(notFoundQueue)
                .to(notFoundExchange);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).withQueueName();
    }

    @Bean
    public ClassMapper classMapper(){
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("ru.tinkoff.edu.java.scrapper.dto.client.LinkUpdate", LinkUpdate.class);
        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("ru.tinkoff.edu.java.scrapper.dto.*");
        classMapper.setIdClassMapping(mappings);
        return classMapper;
    }

    @Bean
    public MessageConverter jsonMessageConverter(ClassMapper classMapper) {
        Jackson2JsonMessageConverter json = new Jackson2JsonMessageConverter();
        json.setClassMapper(classMapper);
        return json;
    }

    @Bean
    public ListenerRabbit rabbitListener(UpdateService updateService){
        return new ListenerRabbit(updateService);
    }
}
