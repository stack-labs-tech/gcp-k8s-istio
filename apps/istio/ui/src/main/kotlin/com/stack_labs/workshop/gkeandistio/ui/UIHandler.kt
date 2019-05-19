package com.stack_labs.workshop.gkeandistio.ui

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono
import java.time.ZonedDateTime.now

/**
 * Created by kevin on 2019-01-13
 */

@Configuration
class AppConfiguration {
    @Bean
    fun routes(UI: UIHandler) = router {
        GET("/", UI::serve)
    }
}

@Component
class UIHandler(val search: SearchService, prop: UIProperties) {

    private val log = LoggerFactory.getLogger(UIHandler::class.java)

    val version = prop.version

    fun serve(serverRequest: ServerRequest): Mono<ServerResponse> = search
            .search()
            .map { it.copy(from = "ui ($version) => ${it.from}", date = now()) }
            .doOnNext { log.info("UI service in version $version called and answered with $it") }
            .flatMap { ServerResponse.ok().syncBody(it) }
            .doOnSubscribe { log.info("UI Service in version $version starting...") }
}
