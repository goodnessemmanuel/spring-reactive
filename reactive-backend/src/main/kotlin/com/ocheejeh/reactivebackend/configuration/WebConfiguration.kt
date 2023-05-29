package com.ocheejeh.reactivebackend.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.filter.CorsFilter

@Configuration
class WebConfiguration {

    /**
     * corsWebFilter - bean function to enable CORS
     * Allow cross-origin resource sharing
     * between running local backend
     * and frontend(Angular)
     */
    @Bean
    fun corsFilter(): CorsWebFilter {
        val config: CorsConfiguration = CorsConfiguration()
        with(config) {
            allowCredentials = true
            addAllowedOrigin("http://localhost:4200")
            addAllowedHeader("*")
            addAllowedMethod("*")
        }
        return CorsWebFilter { config }
    }
}