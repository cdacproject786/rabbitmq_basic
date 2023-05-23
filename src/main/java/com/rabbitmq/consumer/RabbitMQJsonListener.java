package com.rabbitmq.consumer;

import com.rabbitmq.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQJsonListener {


    @RabbitListener(queues = {"${rabbitmq.queue.name2}"})
    public void jsonListener(User user)
    {
        log.info(String.format("Message consumed -> %s",user.toString()));
    }
}
