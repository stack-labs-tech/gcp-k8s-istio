package com.stack_labs.workshop.gkeandistio.ui

import java.time.ZonedDateTime
import java.time.ZonedDateTime.now

/**
 * Created by kevin on 2019-01-13
 */
data class DemoMessage(val hello: String, val from: String, val date: ZonedDateTime = now())
