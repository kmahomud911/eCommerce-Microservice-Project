package com.shawon.UserService.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {

    @KafkaListener(topics = "user-events", groupId = "user-service-group")
    public void consumeEvent(String message) {
        System.out.println("Consumed message: " + message);
    }
}
