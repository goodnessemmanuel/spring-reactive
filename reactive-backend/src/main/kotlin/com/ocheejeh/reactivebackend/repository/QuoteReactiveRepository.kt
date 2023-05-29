package com.ocheejeh.reactivebackend.repository

import com.ocheejeh.reactivebackend.domain.Quote
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.data.repository.reactive.ReactiveSortingRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface QuoteReactiveRepository : ReactiveMongoRepository<Quote, String> {
    fun findAllByBookIdNotNullOrderByBookIdAsc(page: Pageable) :Flux<Quote>
}