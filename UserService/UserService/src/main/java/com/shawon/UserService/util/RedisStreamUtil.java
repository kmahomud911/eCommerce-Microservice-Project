package com.shawon.UserService.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.connection.stream.StreamReadOptions;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisStreamUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void sendToStream(String streamName, Object data) {
        redisTemplate.opsForStream().add(ObjectRecord.create(streamName, data));
    }

//    public void listenToStream(String streamName, StreamListener<String, ObjectRecord<String, Object>> listener) {
//        redisTemplate.opsForStream().createGroup(streamName, "consumer-group");
//        redisTemplate.opsForStream().read(
//                ObjectRecord.class,
//                StreamReadOptions.empty().block(Duration.ofSeconds(1000)).count(1),
//                StreamOffset.create(streamName, ReadOffset.lastConsumed())
//        ).forEach(record -> listener.onMessage((ObjectRecord<String, Object>) record));
//    }
}
