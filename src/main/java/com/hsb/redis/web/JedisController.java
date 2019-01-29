package com.hsb.redis.web;

import com.hsb.redis.common.JedisCache;
import com.hsb.redis.common.JedisContext;
import com.hsb.redis.common.RedisKeyUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * @author heshengbang
 * 2019/1/29.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */

@RestController
@RequestMapping("/jedis")
public class JedisController {
    @GetMapping("/redisString")
    @JedisCache
    public String redisString(@RequestParam("key") String keyParam, @RequestParam("value") String value) {
        String key = RedisKeyUtil.generator("String", keyParam);
        Jedis jedis = JedisContext.getContext();
        if (jedis != null && jedis.isConnected()) {
            jedis.set(key, value);
            return "存储成功";
        } else {
            return "存储失败";
        }
    }

    @GetMapping("/redisList")
    @JedisCache
    public String redisList(@RequestParam("key") String keyParam, @RequestParam("value") String value) {
        String key = RedisKeyUtil.generator("List", keyParam);
        Jedis jedis = JedisContext.getContext();
        if (jedis != null && jedis.isConnected()) {
            jedis.lpush(key, value);
            return "存储成功";
        } else {
            return "存储失败";
        }
    }

    @GetMapping("/redisHash")
    @JedisCache
    public String redisHash(@RequestParam("key") String keyParam,
                            @RequestParam("field") String field, @RequestParam("value") String value) {
        String key = RedisKeyUtil.generator("Hash", keyParam);
        Jedis jedis = JedisContext.getContext();
        if (jedis != null && jedis.isConnected()) {
            jedis.hset(key, field, value);
            return "存储成功";
        } else {
            return "存储失败";
        }
    }
}
