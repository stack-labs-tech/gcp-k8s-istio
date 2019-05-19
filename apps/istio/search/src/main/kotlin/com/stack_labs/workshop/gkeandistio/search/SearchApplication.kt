package com.stack_labs.workshop.gkeandistio.search

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.router
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.time.Duration.ofMillis

@SpringBootApplication
class SearchApplication

fun main(args: Array<String>) {
    runApplication<SearchApplication>(*args)
}

@Configuration
class AppConfiguration {
    @Bean
    fun routes(search: SearchHandler) = router {
        GET("/", search::serve)
    }
}

@Component
class SearchHandler(prop: SearchProperties) {

    private val log = LoggerFactory.getLogger(SearchHandler::class.java)

    val version = prop.version
    val event = prop.event

    val requestWaitingRange = (0..1000).map(Int::toLong)
    val errorRate = prop.errorRate * 10

    fun serve(serverRequest: ServerRequest): Mono<ServerResponse> {

        val duration = requestWaitingRange.shuffled().first()
        val error = requestWaitingRange.shuffled().first() > errorRate

        return if (error) {
            ok()
                    .syncBody(DemoMessage(event, "search ($version)"))
                    .delayElement(ofMillis(duration))
                    .doOnNext { log.info("Search service called and respond with event \"$event:$version\" ") }
        } else {
            ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Random error 🔥").toMono()
        }
    }
}

data class DemoMessage(val hello: String, val from: String)
