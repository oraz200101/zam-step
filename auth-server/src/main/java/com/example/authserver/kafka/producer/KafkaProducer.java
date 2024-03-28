package com.example.authserver.kafka.producer;

import com.example.authserver.models.dtos.UserRegistrationKafka;

public interface KafkaProducer {

    void sendModelUserRegistration(UserRegistrationKafka registrationKafka);

}
