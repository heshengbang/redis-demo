# redis-demo
simple demo for redis

# 4 questions for redis
- 为什么你需要用到Redis？(why you need redis?)
需要从两个角度来思考：其一，为什么要使用缓存？其二，相比其他缓存框架为什么要使用Redis，技术选型上主要考虑了哪些方面？

- Redis支持哪些数据类型？(what data types does Redis support?)
目前已知的五种：普通的String型，Hash型，List型，Set型，ZSet型。另外有一些可扩展的类型，都是在这五种的基础上建立起来的。

- Redis的过期策略如何工作的？(how does the Redis expiration strategy work?)
Redis支持两种过期删除策略：定期删除、惰性删除。
    - 定期删除策略因为会定期随机轮询缓存，查看是否过期，因此会消耗大量的cpu时间，并不适合直接使用。
    - 惰性删除是指用户请求访问缓存时，检查缓存是否过期，如果过期就删除，如果没过期就返回value。

通常情况下会使用定期删除配合惰性删除，但是即便这样，如果定期删除没有随机到缓存并且又长时间没有去访问这些缓存，它们就将积累起来，导致内存越来越多。

- Redis内存淘汰策略如何工作？(how does the Redis elimination strategy work?)
内存淘汰策略众多，直接在redis.conf里面配置即可，我查看了公司开发环境的Redis，发现策略是noeviction。这意味着如果内存不够时，Redis就会报错，这如果应用到生产上应该会带来很大的问题。
通常情况下，可配置为volatile-lru，当内存不足时，会挑选最近最少被访问的缓存删除掉。