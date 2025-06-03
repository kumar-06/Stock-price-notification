package com.stock.stock.monitoring.service;

import java.util.Set;

public interface RedisService {

    <T> void save(String key, String prefix, long ttlMinutes, T value);
    <T> T findByKey(String key, String prefix, Class<T> clazz);
    void deleteByKey(String key, String prefix);
    void deleteAllKey();
    void evictKeysWithPrefix(String prefix);


}
