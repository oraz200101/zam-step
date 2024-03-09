package com.example.authserver.kafka.producer.impl;

import com.example.authserver.kafka.KafkaTopics;
import com.example.authserver.kafka.producer.KafkaProducer;
import com.example.authserver.models.dtos.UserRegistrationRequest;
import com.example.authserver.utils.ObjectMapperHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducerImpl implements KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendModelUserRegistration(UserRegistrationRequest registrationRequest) {
        kafkaTemplate.send(KafkaTopics.TOPIC_USER_REGISTRATION, ObjectMapperHolder.writeJson(registrationRequest));
    }
}
