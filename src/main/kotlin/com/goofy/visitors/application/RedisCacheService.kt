package com.goofy.visitors.application

import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisCacheService(
    private val reactiveRedisTemplate: ReactiveRedisTemplate<String, String>
) {
    companion object {
        private const val VISITORS_DAILY_KEY = "visitors:daily"
        private const val VISITORS_TOTAL_KEY = "visitors:total"
    }

    fun getVisitStatics(target: String) = reactiveRedisTemplate.opsForValue()
        .multiGet(
            listOf(
                "$VISITORS_DAILY_KEY:$target",
                "$VISITORS_TOTAL_KEY:$target"
            )
        )

    fun visitDaily(target: String) = "$VISITORS_DAILY_KEY:$target".increment()

    fun visitTotal(target: String) = "$VISITORS_TOTAL_KEY:$target".increment()

    private fun String.increment() = reactiveRedisTemplate.opsForValue().increment(this)
}
