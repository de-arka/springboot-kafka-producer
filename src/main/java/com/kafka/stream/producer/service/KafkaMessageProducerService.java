package com.kafka.stream.producer.service;

import com.kafka.stream.model.CustomMessage;
import com.kafka.stream.producer.driver.MessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Kafka Message Producer Service
 */
@Slf4j
@Service
public class KafkaMessageProducerService {

    /**
     * Injecting MessageProducer Component
     */
    @Autowired
    private MessageProducer producer;

    /**
     * Method to send message to topic
     * @param message
     * @return
     */
    public String sendMessageToTopic(CustomMessage message) {
        return producer.sendMessageToTopic(message);
    }

}
