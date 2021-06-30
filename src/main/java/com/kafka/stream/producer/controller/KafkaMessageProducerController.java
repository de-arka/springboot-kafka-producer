package com.kafka.stream.producer.controller;

import com.kafka.stream.model.CustomMessage;
import com.kafka.stream.producer.service.KafkaMessageProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller to receive Event through REST API Request Handling
 */
@RestController
@RequestMapping("/v1")
public class KafkaMessageProducerController {

    /**
     * Injects instance of Service Class
     */
    @Autowired
    private KafkaMessageProducerService service;

    /**
     * Method to handle Post Request of the Event and forward the Event to Service level
     * @param message
     * @return
     */
    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessageToTopic(@RequestBody CustomMessage message) {
        return new ResponseEntity<>(service.sendMessageToTopic(message), HttpStatus.OK);
    }

}
