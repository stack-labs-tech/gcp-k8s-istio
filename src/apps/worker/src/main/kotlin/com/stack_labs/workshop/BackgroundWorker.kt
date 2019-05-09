package com.stack_labs.workshop

import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection
import io.micronaut.scheduling.annotation.Scheduled
import io.reactivex.Single
import org.slf4j.LoggerFactory
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import javax.inject.Singleton

@Singleton
class BackgroundWorker(
        private val travelClient: TravelGeneratorClient,
        private val redisClient: RedisClient
) {
    private var redisConnection: StatefulRedisConnection<String, String>? = null

    companion object {
        private val LOG = LoggerFactory.getLogger(BackgroundWorker::class.java)
    }

    @PostConstruct
    fun openConnection() {
        redisConnection = redisClient.connect()
    }

    @Scheduled(fixedRate = "10s")
    fun generateTravel() {
        LOG.info("Will now generate a new travel...")
        travelClient.generateTravel("Stack Labs")
                .doOnSuccess { travel -> LOG.info("Going to save new travel : $travel") }
                .map { travel -> redisConnection?.async()?.set(travel.id, travel.toString())?.toCompletableFuture() }
                .flatMap { future -> Single.fromFuture(future) }
                .doOnSuccess { LOG.info("Succesfully saved travel...") }
                .subscribe()
    }

    @Scheduled(fixedRate = "15s")
    fun printKeys() {
        LOG.info("=================================")
        LOG.info("Showing all existing keys :")
        redisConnection?.sync()?.keys("*")
                ?.onEach { key -> LOG.info("Key : $key") }
        LOG.info("=================================")
    }

    @PreDestroy
    fun closeConnection() = redisConnection?.close()
}