package com.example.brule.core.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

@AllArgsConstructor
public class RedisService {

    RedisTemplate<String, Object> redisTemplate;

    /**
     * 保存属性
     */
    public void set(String key, Object value, long time) {
        if (time == -1) {
            set(key, value);
            return;
        }
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 保存属性
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取属性
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    /**
     * 删除属性
     */
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }


    /**
     * 批量删除属性
     */
    public Long del(List<String> keys) {
        return redisTemplate.delete(keys);
    }


    /**
     * 设置过期时间
     */
    public Boolean expire(String key, long time) {
        if (time == -1) {
            return true;
        }
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }


    /**
     * 获取过期时间
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    /**
     * 判断是否有该属性
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 按delta递增
     */
    public Long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }


    /**
     * 按delta递增 并设置过期时间
     */
    public Long incr(String key, long delta, long time) {
        Long increment = incr(key, delta);
        expire(key, time);
        return increment;
    }


    /**
     * 按delta递减
     */
    public Long decr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, -delta);
    }

}
