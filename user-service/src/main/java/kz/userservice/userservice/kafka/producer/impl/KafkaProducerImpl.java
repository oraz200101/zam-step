package kz.userservice.userservice.kafka.producer.impl;

import kz.userservice.userservice.annotations.Producer;
import kz.userservice.userservice.kafka.KafkaTopics;
import kz.userservice.userservice.kafka.producer.KafkaProducer;
import kz.userservice.userservice.models.kafka.UserUpdateKafka;
import kz.userservice.userservice.utils.ObjectMapperHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@Producer
@RequiredArgsConstructor
public class KafkaProducerImpl implements KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendUserUpdate(UserUpdateKafka userUpdateKafka) {
        kafkaTemplate.send(KafkaTopics.TOPIC_AUTH_UPDATE, ObjectMapperHolder.writeJson(userUpdateKafka));
    }
}
