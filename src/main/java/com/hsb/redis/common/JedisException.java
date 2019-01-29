package com.hsb.redis.common;

/**
 * @author heshengbang
 * 2019/1/29.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */
public class JedisException extends Exception {
    private String msg;
    public JedisException(String msg) {
        this.msg = msg;
    }
}
