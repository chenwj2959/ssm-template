package com.cwj.ssm.template.framework.redis;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component("iCache")
public class RedisCache implements ICache {

    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }
    
    @Override
    public boolean expire(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    @Override
    public boolean set(String key, Object value) {
        boolean flag = true;
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception ex) {
            flag = false;
            logger.error(ex.getMessage(), ex);
        }

        return flag;
    }

    @Override
    public boolean set(String key, Object value, Long expireTime) {
        boolean flag = true;
        try {
            redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
        } catch (Exception ex) {
            flag = false;
            logger.error(ex.getMessage(), ex);
        }

        return flag;
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key, Class<T> t) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void remove(String key) {
        if (this.exists(key)) {
            redisTemplate.delete(key);
        }
    }

    @Override
    public void remove(String... keys) {
        for (String key : keys) {
            this.remove(key);
        }
    }

    @Override
    public void removePattern(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

    @Override
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void hset(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    @Override
    public Object hget(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    @Override
    public void hdel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    @Override
    public Map<Object, Object> hgetall(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public long lpush(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public Object lpop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public long rpush(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public Object rpop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public void sadd(String key, String member) {
        redisTemplate.opsForSet().add(key, member);
    }

    @Override
    public Set<Object> smemebers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public void zadd(String key, double score, String member) {
        redisTemplate.opsForZSet().add(key, member, score);
    }

    @Override
    public Set<Object> zrange(String key, double start, double stop) {
        return redisTemplate.opsForZSet().rangeByScore(key, start, stop);
    }

}
