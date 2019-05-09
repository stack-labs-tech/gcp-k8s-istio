package com.stack_labs.workshop

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single

@Client("\${travel-generator.url}")
interface TravelGeneratorClient {

    @Get("/travels/generate?user={user}")
    fun generateTravel(user: String): Single<Travel>
}