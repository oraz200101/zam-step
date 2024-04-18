package kz.userservice.userservice.models.kafka;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateKafka {
    private Long   id;
    private String email;
    private String password;
}
