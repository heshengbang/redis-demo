package com.hsb.redis.common;

import redis.clients.jedis.Jedis;

/**
 * @author heshengbang
 * 2019/1/29.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */
public class JedisContext {
    private static Jedis localJedis;
    // 包可见，防止被滥用
    static void setContext(Jedis jedis) {
        localJedis = jedis;
    }
    public static Jedis getContext() {
        return localJedis;
    }
}
