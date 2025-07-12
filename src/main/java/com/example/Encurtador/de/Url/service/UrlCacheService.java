package com.example.Encurtador.de.Url.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class UrlCacheService {

    private static final String KEY_PREFIX = "slug:";

    private final RedisTemplate<String, Object> redisTemplate;

    public UrlCacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String getSlugFromCache(String slug){
        Object value = redisTemplate.opsForValue().get(KEY_PREFIX + slug);
        return value != null ? value.toString() : null;
    }

    public void putSlugToCache(String slug, String url, Duration ttl){
        redisTemplate.opsForValue().set(KEY_PREFIX + slug, url, ttl);
    }

    public void putSlugToCache(String slug, String url){
        redisTemplate.opsForValue().set(KEY_PREFIX + slug, url);
    }




}
