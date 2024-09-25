package io.github.glebchanskiy.kafka_producer.producer;

import org.apache.kafka.common.Uuid;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.thedeanda.lorem.Lorem;

import io.github.glebchanskiy.kafka_producer.event.MessageCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
@ConditionalOnProperty(value = "app.mode", havingValue = "producer", matchIfMissing = true)
public class SheduledProducer {

    private final MessageProducer messageProducer;
    private final Lorem lorem;

    @Scheduled(fixedRateString = "${app.consume.rate}")
    void sendMessage() {
        var message = lorem.getWords(5, 10);

        messageProducer.sendMessage(MessageCreatedEvent.builder()
                .eventType("MESSAGE_CREATED_EVENT")
                .eventId(Uuid.randomUuid().toString())
                .message(message)
                .build());
    }
}