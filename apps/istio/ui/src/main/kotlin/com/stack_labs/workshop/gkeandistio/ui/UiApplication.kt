package com.stack_labs.workshop.gkeandistio.ui

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UiApplication

fun main(args: Array<String>) {
    runApplication<UiApplication>(*args)
}
