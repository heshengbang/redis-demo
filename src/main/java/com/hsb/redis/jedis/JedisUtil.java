package com.hsb.redis.jedis;

import com.hsb.redis.common.JedisException;
import redis.clients.jedis.Jedis;

/**
 * @author heshengbang
 * 2019/1/29.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */
public class JedisUtil {

    public static Jedis getJedis() throws JedisException {
        Jedis jedis = new Jedis("localhost", 6379);
        // ping() connect()都可以帮助建立连接
        jedis.connect();
        if (jedis.isConnected()) {
            return jedis;
        } else {
            throw new JedisException("无法建立连接");
        }
    }
}
