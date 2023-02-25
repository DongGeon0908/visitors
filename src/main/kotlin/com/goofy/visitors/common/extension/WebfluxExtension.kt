package com.goofy.visitors.common.extension

import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Mono

fun ServerRequest.param(name: String): String = this.queryParam(name).orElseThrow()

fun <T> T.response(): Mono<T> = Mono.justOrEmpty(this)
