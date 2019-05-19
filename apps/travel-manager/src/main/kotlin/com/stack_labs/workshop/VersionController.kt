package com.stack_labs.workshop

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.Single

@Controller("/version")
class VersionController() {

    @Get("/")
    fun who(): String = "0.0.2"
}