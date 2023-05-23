package com.rabbitmq.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMqProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.binding.key1}")
    private String routingKey;

    //Use Rabbit Template for sending messages

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message)
    {
        log.info(String.format("message sent -> %s",message));
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }

}
