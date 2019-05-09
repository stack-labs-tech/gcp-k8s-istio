package com.stack_labs.workshop

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single

@Client("\${distance-calculator.url}")
interface DistanceCalculatorClient {

    @Get("/distance?startPlace={startPlace}&endPlace={endPlace}")
    fun distance(startPlace: String, endPlace: String): Single<Int>
}