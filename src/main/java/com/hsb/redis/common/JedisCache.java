package com.hsb.redis.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;

/**
 * 提供Jedis上下文的写入
 * 凡是标注了该注解的方法，都可以在方法中从JedisContext中获取到Jedis实例
 * 通过aop的方式完成jedis在Context中的创建和释放
 * Jedis实例不用关闭资源，资源将被自动释放
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface JedisCache {
    @AliasFor("value")
    String keyPrefix() default "";
    @AliasFor("keyPrefix")
    String value() default "";
}
