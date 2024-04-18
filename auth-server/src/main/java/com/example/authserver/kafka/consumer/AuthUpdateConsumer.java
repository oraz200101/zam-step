package com.example.authserver.kafka.consumer;

import com.example.authserver.annotations.Consumer;
import com.example.authserver.kafka.KafkaTopics;
import com.example.authserver.models.dtos.AuthUpdateDto;
import com.example.authserver.services.AuthService;
import com.example.authserver.utils.ObjectMapperHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;

@Consumer
@RequiredArgsConstructor
public class AuthUpdateConsumer {

    private final AuthService authService;

    @KafkaListener(id = "auth-update-consumer", topics = KafkaTopics.TOPIC_AUTH_UPDATE)
    public void consume(String value) {
        AuthUpdateDto updateDto = ObjectMapperHolder.readJson(value, AuthUpdateDto.class);

        authService.update(updateDto);
    }
}
