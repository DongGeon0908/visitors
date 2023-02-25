package com.goofy.visitors.router

import com.goofy.visitors.handler.VisitorsHandler
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Configuration
class VisitorsRouter(
    private val visitorsHandler: VisitorsHandler
) {
    @Bean
    @ConditionalOnMissingBean(name = ["routeVisitors"])
    fun routeVisitors() = nest(
        path("/api/v1/visitors"),
        router {
            GET("", visitorsHandler::visit)
        }
    )
}
