package com.stack_labs.workshop

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.Flowable
import io.reactivex.Single
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

@Controller("/")
class DistanceController(val config: Config) {

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(DistanceController::class.java)
        val random = Random(12345L)
    }

    @Get("/distance")
    fun resolveDistance(from: String, to: String): Single<Int> =
            Flowable.fromIterable(config.distances.distances)
                    .filter { d -> hasEntry(d, from, to) }
                    .map { d -> d.distance }
                    .first(random.nextInt(900) + 100)
                    .doOnSuccess { dist -> LOG.info("Resolved distance between $from and $to : $dist km") }

    private fun hasEntry(cityDistance: CityDistance, from: String, to:String) =
            (cityDistance.from.equals(from, ignoreCase = true) && cityDistance.to.equals(to, ignoreCase = true))
            || (cityDistance.from.equals(to, ignoreCase = true) && cityDistance.to.equals(from, ignoreCase = true))
}