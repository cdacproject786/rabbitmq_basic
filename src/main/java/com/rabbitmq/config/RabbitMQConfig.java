package com.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name1}")
    private String queue1;

    @Value(("${rabbitmq.queue.name2}"))
    private String queue2;

    @Value("${rabbitmq.exchange.name}")
    private String topicExchange;

    @Value("${rabbitmq.binding.key1}")
    private String routingKey1;

    @Value("${rabbitmq.binding.key2}")
    private String routingKey2;

    //spring bean for rabbitmq queue
    @Bean
    public Queue queue()
    {
        return new Queue(queue1);
    }

    @Bean
    public Queue jsonQueue()
    {
        return new Queue(queue2);
    }

    @Bean
    public TopicExchange exchange()
    {
        return new TopicExchange(topicExchange);
    }

    //binding queue with exchange
    @Bean
    public Binding binding()
    {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey1);
    }

    //biding json queue with exchange
    @Bean
    public Binding jsonBinding()
    {
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(routingKey2);
    }

    @Bean
    public MessageConverter messageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    //configuring connection factory
    //RabbitTemplate
    //RabbitAdmin

    //Spring boot autoconfiguration automatically configure these 3 beans

}
