package com.hsb.redis.web;

import com.hsb.redis.util.RedisKeyUtil;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * @author heshengbang
 * 2019/1/26.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */
@RestController
public class HelloController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/redisString")
    public String redisString(@RequestParam("key") String keyParam, @RequestParam("value") String value) {
        String key = RedisKeyUtil.generator("String", keyParam);
        redisTemplate.opsForValue().set(key, value);
        return "存储成功";
    }

    @GetMapping("/redisList")
    public String redisList(@RequestParam("key") String keyParam, @RequestParam("value") String value) {
        String key = RedisKeyUtil.generator("List", keyParam);
        redisTemplate.opsForList().leftPush(key, value);
        List<String> values = redisTemplate.opsForList().range(key, 0, 3);
        return "取出元素：" + values;
    }

    @GetMapping("/redisHash")
    public String redisHash(@RequestParam("key") String keyParam,
                            @RequestParam("field") String field, @RequestParam("value") String value) {
        String key = RedisKeyUtil.generator("Hash", keyParam);
        redisTemplate.opsForHash().put(key, field, value);
        Map<Object, Object> fieldsAndValues = redisTemplate.opsForHash().entries(key);
        StringBuilder sb = new StringBuilder();
        fieldsAndValues.forEach((k, v) -> sb.append(" |").append(k).append(" ").append(v));
        return "取出元素：" + sb.toString();
    }

    @GetMapping("/redisSet")
    public String redisSet(@RequestParam("key") String keyParam, @RequestParam("value") String value) {
        String key = RedisKeyUtil.generator("Set", keyParam);
        try {
            long result = redisTemplate.opsForSet().add(key, value);
            if (result == 1) {
                Set<String> values = redisTemplate.opsForSet().members(key);
                StringBuilder sb = new StringBuilder(" ");
                values.forEach(item -> {
                    sb.append(item).append(" | ");
                });
                return "存入成功，已有元素为：" + sb.toString();
            } else if (result == 0){
                return "元素已存在，无法继续存入";
            } else {
                return "未知返回码：" + result;
            }
        } catch (Exception e) {
            return key + " 对应的set集合不存在，获得错误：" + e.getClass().getSimpleName();
        }
    }

    @GetMapping("/redisZSet")
    public String redisZSet(@RequestParam("key") String keyParam, @RequestParam("value") String value, @RequestParam("score") Double score) {
        String key = RedisKeyUtil.generator("ZSet", keyParam);
        try {
            boolean result = redisTemplate.opsForZSet().add(key, value, score);
            if (result) {
                Set<String> values = redisTemplate.opsForZSet().range(key, 1,3);
                StringBuilder sb = new StringBuilder(" ");
                values.forEach(item -> sb.append(item).append(" | "));
                return "存入成功，已有元素为：" + sb.toString();
            } else {
                return "元素已存在，无法继续存入";
            }
        } catch (Exception e) {
            return key + " 对应的set集合不存在，获得错误：" + e.getClass().getSimpleName();
        }
    }
}
