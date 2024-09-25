package io.github.glebchanskiy.kafka_producer.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageCreatedEvent {
    private String eventType;
    private String eventId;
    private String message;
}
