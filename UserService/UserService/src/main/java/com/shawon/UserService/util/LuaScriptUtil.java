package com.shawon.UserService.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

@Component
public class LuaScriptUtil {

    @Autowired
    private RedisConnectionFactory connectionFactory;

    public void executeLuaScript(String script, String key, String value) {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(script);
        redisScript.setResultType(Long.class);
        connectionFactory.getConnection().eval(
                redisScript.getScriptAsString().getBytes(),
                ReturnType.INTEGER,
                1,
                key.getBytes(),
                value.getBytes()
        );
    }
}
