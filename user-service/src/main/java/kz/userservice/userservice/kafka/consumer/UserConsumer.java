package kz.userservice.userservice.kafka.consumer;

import kz.userservice.userservice.annotations.Consumer;
import kz.userservice.userservice.kafka.KafkaTopics;
import kz.userservice.userservice.models.dtos.UserRegistrationDto;
import kz.userservice.userservice.services.UserService;
import kz.userservice.userservice.utils.ObjectMapperHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;

@Consumer
@RequiredArgsConstructor
public class UserConsumer {

    private final UserService service;

    @KafkaListener(id = "user-registration-consumer", topics = KafkaTopics.TOPIC_USER_REGISTRATION, containerFactory = "containerFactory")
    public void consume(String value) {
        UserRegistrationDto user = ObjectMapperHolder.readJson(value, UserRegistrationDto.class);

        service.createUser(user);
    }


}
