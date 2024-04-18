package com.example.authserver.kafka.producer.impl;

import com.example.authserver.annotations.Producer;
import com.example.authserver.kafka.KafkaTopics;
import com.example.authserver.kafka.producer.KafkaProducer;
import com.example.authserver.models.kafka.UserRegistrationKafka;
import com.example.authserver.utils.ObjectMapperHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@Producer
@RequiredArgsConstructor
public class KafkaProducerImpl implements KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendModelUserRegistration(UserRegistrationKafka registrationKafka) {
        kafkaTemplate.send(KafkaTopics.TOPIC_USER_REGISTRATION, ObjectMapperHolder.writeJson(registrationKafka));
    }
}
