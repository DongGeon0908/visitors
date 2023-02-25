package com.goofy.visitors.handler

import com.goofy.visitors.application.RedisCacheService
import com.goofy.visitors.common.extension.ok
import com.goofy.visitors.common.extension.param
import com.goofy.visitors.dto.VisitorsResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class VisitorsHandler(
    private val redisCacheService: RedisCacheService
) {
    fun visit(request: ServerRequest): Mono<ServerResponse> {
        val target = request.param("target")
        return redisCacheService.visitDaily(target)
            .publish { redisCacheService.visitTotal(target) }
            .flatMap { redisCacheService.getVisitStatics(target) }
            .flatMap { VisitorsResponse.of(it[0], it[1]).ok() }
    }
}
