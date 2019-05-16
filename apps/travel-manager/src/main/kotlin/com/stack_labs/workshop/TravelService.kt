package com.stack_labs.workshop

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.reactivex.Flowable
import io.reactivex.Single
import org.apache.commons.lang3.RandomStringUtils
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Singleton

@Singleton
class TravelService(
        private val distanceClient: DistanceCalculatorClient,
        private val redisClient: RedisClient) {

    private val redisConnection: StatefulRedisConnection<String, String> = redisClient.connect()
    private val objectMapper = jacksonObjectMapper()

    companion object {
        val LOG = LoggerFactory.getLogger(TravelService::class.java)
    }

    fun generateAndStoreTravel(user: String): Single<String>? = generateTravel(user)
                .doOnSuccess { travel -> LOG.info("Going to save new travel : $travel") }
                .map { travel -> redisConnection.async()?.set(travel.id, jacksonObjectMapper().writeValueAsString(travel))?.toCompletableFuture() }
                .flatMap { future -> Single.fromFuture(future) }
                .doOnSuccess { LOG.info("Successfully saved travel...") }

    fun getTravels() =
            Flowable.fromIterable(redisConnection.sync().keys("*"))

    private fun generateTravel(user: String): Single<Travel> {
        val id = RandomStringUtils.randomAlphanumeric(20)
        val startPlace = "Toulouse"
        val endPlace = "Paris"

        LOG.info("Requesting distance from $startPlace to $endPlace...")
        return distanceClient.distance(startPlace, endPlace)
                .map { dist -> Travel(id, user, Date(1212580300L), Date(1212980300L), startPlace, endPlace, dist) }
    }

    fun getTravel(travelId: String): Single<Travel> {
        return Single.just(redisConnection.sync()?.get(travelId))
                .map { t -> jacksonObjectMapper().readValue<Travel>(t) }
    }
}