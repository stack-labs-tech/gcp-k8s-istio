package com.stack_labs.workshop
import com.fasterxml.jackson.module.kotlin.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.ClassUtil
import io.micronaut.context.annotation.Value
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.util.*
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