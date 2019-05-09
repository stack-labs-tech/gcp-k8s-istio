package com.stack_labs.workshop

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.Single
import org.slf4j.LoggerFactory
import java.util.*

@Controller("/")
class DistanceController() {

    companion object {
        val LOG = LoggerFactory.getLogger(DistanceController::class.java)
        val random = Random(12345L)
    }

    // TODO Add a smarter calculation...
    @Get("/distance")
    fun generateTravel(startPlace: String, endPlace: String): Single<Int> =
            Single.just(random.nextInt(900) + 100)
}