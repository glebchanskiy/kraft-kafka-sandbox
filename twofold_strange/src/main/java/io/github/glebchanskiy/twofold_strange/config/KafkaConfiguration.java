package io.github.glebchanskiy.kafka_producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

    @Value("${topic.name}")
    private String topicName;

    @Value("${topic.partitions}")
    private Integer partitions;

    @Value("${topic.replicas}")
    private Integer replicas;


    @Bean
    NewTopic topic() {
        return TopicBuilder.name(topicName)
                .partitions(partitions)
                .replicas(replicas)
                .build();
    }
}
