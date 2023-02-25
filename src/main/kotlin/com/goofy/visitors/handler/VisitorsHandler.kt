package com.goofy.visitors.handler

import com.goofy.visitors.common.extension.param
import com.goofy.visitors.domain.Visitors
import com.goofy.visitors.infrastructure.VisitorsRepository
import mu.KotlinLogging
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class VisitorsHandler(
    private val reactiveRedisTemplate: ReactiveRedisTemplate<String, String>,
    private val visitorsRepository: VisitorsRepository
) {
    private val logger = KotlinLogging.logger {}

    fun visit(request: ServerRequest): Mono<ServerResponse> {
        val target = request.param("target")

        visitorsRepository.save(Visitors(target = target))

        return ServerResponse.ok().body(
            visitorsRepository.save(Visitors(target = target)),
            Visitors::class.java
        )
    }
}
