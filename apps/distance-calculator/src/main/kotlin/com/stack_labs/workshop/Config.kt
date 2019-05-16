package com.stack_labs.workshop
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.micronaut.context.annotation.Value
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import javax.inject.Singleton

@Singleton
class Config(
        @Value("\${distances.config.file}") var configFile: String?) {

    companion object {
        val LOG: Logger = LoggerFactory.getLogger(DistanceController::class.java)
    }

    val distances: Distances

    init {
        LOG.info("Configuring distances with file $configFile...")
        distances = when (configFile) {
            null -> Distances(emptyList())
            else -> jacksonObjectMapper().readValue(File(configFile))
        }
        LOG.info("Distances configured, ${distances.distances.size} distances loaded...")
    }
}