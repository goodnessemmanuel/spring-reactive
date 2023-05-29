package com.ocheejeh.reactivebackend

import com.ocheejeh.reactivebackend.configuration.QuoteLoader
import com.ocheejeh.reactivebackend.repository.QuoteReactiveRepository
import jakarta.annotation.PostConstruct
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class ReactiveBackendApplication
fun main(args: Array<String>) {
	runApplication<ReactiveBackendApplication>(*args)
}