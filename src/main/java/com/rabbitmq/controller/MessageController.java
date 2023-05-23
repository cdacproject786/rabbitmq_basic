package com.rabbitmq.controller;

import com.rabbitmq.dto.User;
import com.rabbitmq.publisher.RabbitMQJsonProducer;
import com.rabbitmq.publisher.RabbitMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    @Autowired
    private RabbitMqProducer producer;

    @Autowired
    private RabbitMQJsonProducer jsonProducer;

    @GetMapping("/publish")
    public ResponseEntity<?> sendMessage(@RequestParam("message") String message)
    {
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent successfully.....");
    }


    @PostMapping("/jsonpublish")
    public ResponseEntity<?> sendJasonMessage(@RequestBody User user)
    {
        jsonProducer.sendMessage(user);
        return new ResponseEntity<>("Json message sent successfully", HttpStatus.OK);
    }
}
