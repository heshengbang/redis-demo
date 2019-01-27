package com.hsb.redis.util;

/**
 * @author heshengbang
 * 2019/1/26.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */
public class RedisKeyUtil {
    public static String generator(String... keys) {
        StringBuilder result = new StringBuilder();
        for (String key : keys) {
            result.append(key).append(":");
        }
        if (result.toString().endsWith(":")) {
            result.deleteCharAt(result.lastIndexOf(":"));
        }
        return result.toString();
    }
}
