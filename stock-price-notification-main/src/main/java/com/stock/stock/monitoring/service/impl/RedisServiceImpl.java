package com.stock.stock.monitoring.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.stock.monitoring.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Set;



@Service
@Slf4j
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> void save(String key, String prefix, long ttlMinutes, T value) {
        String redisKey = prefix + "-" + key;
        try {
            redisTemplate.opsForValue().set(redisKey, value, Duration.ofMinutes(ttlMinutes));
            log.info("cache saved for key-{} value-{}", redisKey, value);
        } catch (Exception e) {
            log.error("Exception while saving value into redis for key-{}", redisKey, e);
        }
    }

    @Override
    public <T> T findByKey(String key, String prefix, Class<T> clazz) {
        String redisKey = prefix + "-" + key;
        try {
            Object result = redisTemplate.opsForValue().get(redisKey);
            return objectMapper.convertValue(result, clazz);
        } catch (Exception e) {
            log.error("Exception while fetching value from redis for key-{}", redisKey, e);
        }
        return null;
    }

    @Override
    public void deleteByKey(String key, String prefix) {
        String redisKey = prefix + "-" + key;
        redisTemplate.delete(redisKey);
    }

    @Override
    public void deleteAllKey() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @Override
    public void evictKeysWithPrefix(String prefix) {
        try {
            Set<String> keys = redisTemplate.keys(prefix + "*");
            if (keys != null) {
                redisTemplate.delete(keys);
            }
        } catch (Exception e) {
            log.error("Exception while evicting key with prefix-{}", prefix);
        }
    }


}

