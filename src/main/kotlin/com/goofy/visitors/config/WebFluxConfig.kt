package com.goofy.visitors.config

import com.goofy.visitors.common.extension.mapper
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.HttpMessageReader
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.http.codec.multipart.DefaultPartHttpMessageReader
import org.springframework.http.codec.multipart.MultipartHttpMessageReader
import org.springframework.http.codec.multipart.Part
import org.springframework.util.MimeType
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer
import java.nio.charset.StandardCharsets

/** produces default type */
const val MEDIA_TYPE_APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8"

@Configuration
class WebFluxConfig : WebFluxConfigurer {
    /** cors mapping */
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOriginPatterns(CorsConfiguration.ALL)
            .allowedMethods(CorsConfiguration.ALL)
            .allowedHeaders(CorsConfiguration.ALL)
            .allowCredentials(true)
            .maxAge(3600)
    }

    override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
        configurer.defaultCodecs().apply {
            /** jackson encoder */
            this.jackson2JsonEncoder(
                Jackson2JsonEncoder(
                    mapper,
                    MimeType("application", "json"),
                    MimeType("application", "*+json"),
                    MimeType("application", "json", StandardCharsets.UTF_8)
                )
            )

            /** jackson decoder */
            this.jackson2JsonDecoder(Jackson2JsonDecoder(mapper))

            /** default codec */
            this.configureDefaultCodec { codec ->
                if (codec is MultipartHttpMessageReader) {
                    val partReader: HttpMessageReader<Part> = codec.partReader
                    if (partReader is DefaultPartHttpMessageReader) {
                        partReader.setMaxHeadersSize(1024 * 16)
                    }
                }
            }
        }
    }
}
