package com.stack_labs.workshop

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.Single
import jdk.incubator.http.HttpResponse
import org.slf4j.LoggerFactory
import java.util.*

@Controller("/probes")
class ReadinessController() {

    private var isReady: Boolean = true
    private var isAlive: Boolean = true

    @Get("/readiness")
    fun readiness() = if (isReady) HttpStatus.OK else HttpStatus.INTERNAL_SERVER_ERROR

    @Get("/readiness/fail")
    fun failReadiness(): String {
        isReady = false
        return "Readiness has been set to failure !"
    }

    @Get("/readiness/success")
    fun successReadiness(): String {
        isReady = true
        return "Readiness has been set to success !"
    }

    @Get("/liveness")
    fun liveness() = if (isAlive) HttpStatus.OK else HttpStatus.INTERNAL_SERVER_ERROR

    @Get("/liveness/fail")
    fun failLiveness(): String {
        isAlive = false
        return "Liveness has been set to failure !"
    }

    @Get("/liveness/success")
    fun successLiveness(): String {
        isAlive = true
        return "Liveness has been set to success !"
    }
}