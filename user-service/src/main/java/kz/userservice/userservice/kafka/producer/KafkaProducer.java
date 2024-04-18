package kz.userservice.userservice.kafka.producer;

import kz.userservice.userservice.models.kafka.UserUpdateKafka;

public interface KafkaProducer {

    void sendUserUpdate(UserUpdateKafka userUpdateKafka);

}
