package com.stack_labs.workshop.gkeandistio.search

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.router

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

    fun serve(serverRequest: ServerRequest) = ok()
            .syncBody(DemoMessage(event, "search ($version)"))
            .doOnNext { log.info("Search service called and respond with event \"$event:$version\" ") }
}

data class DemoMessage(val hello: String, val from: String)