package com.goofy.visitors.application

import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisCacheService(
    private val reactiveRedisTemplate: ReactiveRedisTemplate<String, String>
) {
    companion object {
        private const val VISITORS_KEY = "visitors:default"
    }

    fun visit(target: String) = "$VISITORS_KEY:$target".increment()

    private fun String.increment() = reactiveRedisTemplate.opsForValue().increment(this)
}
