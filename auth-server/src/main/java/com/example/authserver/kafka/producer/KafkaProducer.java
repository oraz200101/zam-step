package com.example.authserver.kafka.producer;

import com.example.authserver.models.dtos.UserRegistrationRequest;

public interface KafkaProducer {

    void sendModelUserRegistration(UserRegistrationRequest registrationRequest);

}
