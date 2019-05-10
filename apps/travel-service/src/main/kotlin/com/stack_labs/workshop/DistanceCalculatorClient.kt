package com.stack_labs.workshop

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single

@Client("\${distance-calculator.url}")
interface DistanceCalculatorClient {

    @Get("/distance?from={from}&to={to}")
    fun distance(from: String, to: String): Single<Int>
}