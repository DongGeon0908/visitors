package com.goofy.visitors.config

import com.goofy.visitors.common.extension.isProd
import com.goofy.visitors.common.extension.isStaging
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ReactiveStringRedisTemplate

@Configuration
@EnableConfigurationProperties(RedisProperties::class)
class RedisConfig(
    private val environment: Environment,
    private val properties: RedisProperties
) {
    companion object {
        private val host: String = "localhost"
        private val port: Int = 6379
    }

    @Bean
    fun reactiveRedisConnectionFactory(): ReactiveRedisConnectionFactory {
        if (environment.isProd() || environment.isStaging()) {
            return LettuceConnectionFactory(properties.host, properties.port)
        }

        return LettuceConnectionFactory(host, port)
    }

    @Bean
    fun reactiveStringRedisTemplate(): ReactiveStringRedisTemplate {
        return ReactiveStringRedisTemplate(reactiveRedisConnectionFactory())
    }
}
