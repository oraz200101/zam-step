package com.example.authserver.utils;

import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;

public class MyStringSerializer implements Serializer<String> {

    @Override
    public byte[] serialize(String topic, String data) {
        return data.getBytes(StandardCharsets.UTF_8);
    }

}

