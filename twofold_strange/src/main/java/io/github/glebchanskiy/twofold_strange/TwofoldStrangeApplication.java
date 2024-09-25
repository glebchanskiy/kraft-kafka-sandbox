package io.github.glebchanskiy.kafka_producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.gson.Gson;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

@SpringBootApplication
public class TwofoldStrangeApplication {

	@Value("${topic.name}")
    private String topicName;

    @Bean
    Gson gson() {
        return new Gson();
    }

    @Bean
    Lorem lorem() {
        return LoremIpsum.getInstance();
    }

	public static void main(String[] args) {
		SpringApplication.run(TwofoldStrangeApplication.class, args);
	}
}
