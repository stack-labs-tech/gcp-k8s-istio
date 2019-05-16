package com.stack_labs.workshop

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.Flowable
import io.reactivex.Single

@Controller("/travels")
class TravelController(private val travelService: TravelService) {

    @Get("/")
    fun getAll(): Flowable<String> = travelService.getTravels()

    @Get("/{travelId}")
    fun getTravel(travelId: String): Single<Travel> = travelService.getTravel(travelId)
}