package com.stack_labs.workshop.gkeandistio.search

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * Created by kevin on 2019-01-13
 */
@Component
@ConfigurationProperties("search")
class SearchProperties {
    lateinit var version: String
    lateinit var event: String
    var errorRate: Int = 0
    var maxLatency: Int = 1000
}
