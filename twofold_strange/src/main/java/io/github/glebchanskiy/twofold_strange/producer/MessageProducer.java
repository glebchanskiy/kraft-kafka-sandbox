package io.github.glebchanskiy.kafka_producer.producer;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.github.glebchanskiy.kafka_producer.event.MessageCreatedEvent;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "app.mode", havingValue = "producer", matchIfMissing = true)
public class MessageProducer {

    @Value("${topic.name}")
    private String topicName; 

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Gson gson;

    public void sendMessage(MessageCreatedEvent message) {
        kafkaTemplate.send(topicName, getSaltString(), gson.toJson(message));
        System.out.println("produced message [" + message.getEventId() + "] " + message.getMessage());
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
