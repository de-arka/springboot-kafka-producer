package com.kafka.stream.producer.driver;

import com.kafka.stream.model.CustomMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * MessageProducer class that holds the instance of KafkaTemplate and uses it to send the Message to actual topic
 */
@Slf4j
@Component
public class MessageProducer {

    /**
     * Target Topic Name
     */
    private static final String TOPIC_NAME = "kafka-stream-topic";

    /**
     * Inject the Kafka Template into MessageProducer bean
     */
    private final KafkaTemplate<String, CustomMessage> kafkaTemplate;

    /**
     * By default KafkaTemplate<Object, Object> is created by Spring Boot in KafkaAutoConfiguration class.
     * Since Spring considers generic type information during dependency injection the default bean can't be autowired
     * into KafkaTemplate<String, byte[]>.
     *
     * @param kafkaTemplate
     */
    @Autowired
    public MessageProducer(KafkaTemplate<String, CustomMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Method to send Message to target topic
     *
     * @param message
     * @return
     */
    public String sendMessageToTopic(CustomMessage message) {
        final String[] status = {""};
        // the KafkaTemplate provides asynchronous send methods returning a Future
        ListenableFuture<SendResult<String, CustomMessage>> future = kafkaTemplate.send(TOPIC_NAME, message);
        // register a callback with the listener to receive the result of the send asynchronously
        future.addCallback(new ListenableFutureCallback<SendResult<String, CustomMessage>>() {
            @Override
            public void onSuccess(SendResult<String, CustomMessage> result) {
                log.info("Kafka sent message='{}' with offset={}", message,
                        result.getRecordMetadata().offset());
                status[0] = "SUCCESS";
            }
            @Override
            public void onFailure(Throwable ex) {
                log.error("Kafka unable to send message='{}'", message, ex);
                status[0] = "FAILURE";
            }
        });
        return status[0];
    }

}
