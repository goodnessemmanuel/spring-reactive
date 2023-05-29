package com.ocheejeh.reactivebackend.configuration

import com.ocheejeh.reactivebackend.domain.Quote
import com.ocheejeh.reactivebackend.repository.QuoteReactiveRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.function.Supplier

/**
 * the first time this application runs,
 * read the text data in resources
 * and store them in mongoDB as quotes
 */
@Component
class QuoteLoader (private val quoteRxRepository: QuoteReactiveRepository) : ApplicationRunner {
    private val LOG :Logger = LoggerFactory.getLogger(this.javaClass.name)
    private var l: Long = 0L

    private val idSeqSupplier :Supplier<String> = run { Supplier { String.format("%5d", l++) } }

    /**
     * load data only when
     * you there are no
     * quotes in the database
     */
    override fun run(args: ApplicationArguments?) {
        if (quoteRxRepository.count().block() == 0L){
            val bufferReader = this.javaClass.classLoader
                .getResourceAsStream("pg2000.txt")
                ?.bufferedReader()

            bufferReader?.lines()?.let { lines ->
                Flux.fromStream(
                    lines.filter{ it.trim().isNotEmpty() }
                        .map { line -> quoteRxRepository
                            .save( Quote(idSeqSupplier.get(), "El Quote", line)) }
                ).subscribe{ m -> LOG.info("New quote loaded: ${m.block()}") }
                LOG.info("repository now contains ${quoteRxRepository.count().block()} entries")
            }
        }
    }
}