package com.hsb.redis.common;

import com.hsb.redis.jedis.JedisUtil;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @author heshengbang
 * 2019/1/29.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */
@Aspect
@Component
@Slf4j
public class JedisAop {
    @Pointcut("execution(public * com.hsb.redis.web.*Controller.*(..))")
    public void jedisAop(){}

    @Around("jedisAop()")
    public Object getJedis(ProceedingJoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (method != null && method.isAnnotationPresent(JedisCache.class)) {
            try (Jedis jedis = JedisUtil.getJedis()) {
                JedisContext.setContext(jedis);
                return joinPoint.proceed ();
            } catch (Throwable e) {
                log.info("切面获取Jedis失败：", e);
            } finally {
                Jedis jedis = JedisContext.getContext();
                if (jedis != null) {
                    jedis.close();
                }
                JedisContext.setContext(null);
            }
        } else {
            try {
                return joinPoint.proceed ();
            } catch (Throwable e) {
                log.info(e.getMessage());
            }
        }
        return null;
    }
}
