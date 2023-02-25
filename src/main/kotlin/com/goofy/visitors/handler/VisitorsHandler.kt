package com.goofy.visitors.handler

import com.goofy.visitors.application.RedisCacheService
import com.goofy.visitors.common.extension.ok
import com.goofy.visitors.common.extension.param
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest

@Component
class VisitorsHandler(
    private val redisCacheService: RedisCacheService
) {
    fun visit(request: ServerRequest) =
        redisCacheService.visit(
            target = request.param("target")
        ).flatMap { count -> count.ok() }
}
