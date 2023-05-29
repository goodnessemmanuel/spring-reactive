package com.ocheejeh.reactivebackend.controller

import com.ocheejeh.reactivebackend.domain.Quote
import com.ocheejeh.reactivebackend.repository.QuoteReactiveRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.function.Supplier

@RestController
@RequestMapping("/api/v1")
class QuoteReactiveController (private val quoteReactiveRepository: QuoteReactiveRepository) {

    @GetMapping("/quotes-reactive-test")
    fun test() : Mono<String> {
        return Mono.fromSupplier { Supplier{String.format("%s reactive", "Hello mono!")}.get() }
    }

    @GetMapping("/quotes-reactive")
    fun getQuotesFlux() : Flux<Quote> {
        return quoteReactiveRepository
            .findAll()
            .delayElements(Duration.ofMillis(DELAY_PER_ITEM_IN_MILLI_SEC))
    }

    @GetMapping("/quotes-reactive-paged")
    fun getQuotesFluxPaged(@RequestParam(name = "page") pageNo: Int = 1, @RequestParam size: Int = 5 ) : Flux<Quote>{
        return quoteReactiveRepository
            .findAllByBookIdNotNullOrderByBookIdAsc(PageRequest.of(pageNo, size))
            .delayElements(Duration.ofMillis(DELAY_PER_ITEM_IN_MILLI_SEC))
    }

    companion object {
        private const val DELAY_PER_ITEM_IN_MILLI_SEC: Long = 100
    }
}