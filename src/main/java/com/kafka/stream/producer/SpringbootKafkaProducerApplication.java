package com.kafka.stream.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * An Apache Kafka KafkaTemplate-based Producer Application that produces Messages to a Topic
 */
@SpringBootApplication
public class SpringbootKafkaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootKafkaProducerApplication.class, args);
    }

}
