package io.github.glebchanskiy.kafka_producer.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.github.glebchanskiy.kafka_producer.event.MessageCreatedEvent;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "app.mode", havingValue = "consumer", matchIfMissing = true)
public class Consumer {
    
    @Value("${app.consume.rate}")
    private Integer appConsumeRate;

    private final Gson gson;
    
    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.consumer.group-id}", concurrency = "${app.consume.concurrency}")
    void consume(String message) throws InterruptedException {
        Thread.sleep(appConsumeRate);
        var o = gson.fromJson(message, MessageCreatedEvent.class);
        System.out.println("consumed message [" + o.getEventId() + "] " + o.getMessage());
    }
}
