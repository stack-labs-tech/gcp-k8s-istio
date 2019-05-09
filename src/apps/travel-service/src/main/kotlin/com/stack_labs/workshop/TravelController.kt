package com.stack_labs.workshop

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.Single
import org.apache.commons.lang3.RandomStringUtils
import org.slf4j.LoggerFactory
import java.util.*

@Controller("/travels")
class TravelController(private val distanceClient: DistanceCalculatorClient) {
    companion object {
        val LOG = LoggerFactory.getLogger(TravelController::class.java)
    }

    @Get("/generate")
    fun generateTravel(user: String): Single<Travel> {
        val id = RandomStringUtils.randomAlphanumeric(20)
        val startPlace = "Toulouse"
        val endPlace = "Paris"

        LOG.info("Requesting distance from $startPlace to $endPlace...")
        return distanceClient.distance(startPlace, endPlace)
                .map { dist -> Travel(id, user, Date(1212580300L), Date(1212980300L), startPlace, endPlace, dist) }
    }
}