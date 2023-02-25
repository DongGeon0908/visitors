package com.goofy.visitors.common.extension

import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

fun ServerRequest.param(name: String): String = this.queryParam(name).orElseThrow()

fun <T> T.ok() = ServerResponse.ok()
    .contentType(MediaType.APPLICATION_JSON)
    .body(fromValue(this))
