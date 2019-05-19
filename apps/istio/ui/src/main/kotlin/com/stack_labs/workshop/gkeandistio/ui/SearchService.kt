package com.stack_labs.workshop.gkeandistio.ui

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

/**
 * Created by kevin on 2019-01-13
 */
@Component
class SearchService(wcb: WebClient.Builder, prop: UIProperties) {

    private val searchUrl = prop.searchUrl

    private val log = LoggerFactory.getLogger(SearchService::class.java)
    private val wc = wcb.baseUrl(searchUrl).build()

    fun search(): Mono<DemoMessage> {

        log.info("Before call to SearchService at url $searchUrl")

        return wc.get()
                .retrieve()
                .bodyToMono<DemoMessage>()
                .doOnSuccess { log.info("Call made to $searchUrl") }
    }
}
