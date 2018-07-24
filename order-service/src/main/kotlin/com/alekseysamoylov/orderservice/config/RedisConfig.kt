package com.alekseysamoylov.orderservice.config

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
@EnableCaching
class RedisConfig : CachingConfigurerSupport() {
    @Bean
    fun redisConnectionFactory(): JedisConnectionFactory {
        val jedisConnectionFactory = JedisConnectionFactory()
        jedisConnectionFactory.usePool = true
        return jedisConnectionFactory
    }

    @Bean(name = ["productRedisTemplate"])
    fun productRedisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<String, String> {
        val redisTemplate = RedisTemplate<String, String>()
        redisTemplate.connectionFactory = connectionFactory
        return redisTemplate
    }

    @Bean(name = ["productCacheManager"])
    fun productCacheManager(): CacheManager {
        return RedisCacheManager(productRedisTemplate(redisConnectionFactory()))
    }
}
