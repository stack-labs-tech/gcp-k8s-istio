package com.stack_labs.workshop

import io.micronaut.context.annotation.Value
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.Single

@Controller("/who")
class WhoController(private val distanceCalculatorClient: DistanceCalculatorClient,
                    @Value("\${HOSTNAME:unknwown}") private val hostname: String) {

    @Get("/")
    fun who(): Single<String> = distanceCalculatorClient.who()
            .map { t -> "TravelManager instance: $hostname - $t" }
}