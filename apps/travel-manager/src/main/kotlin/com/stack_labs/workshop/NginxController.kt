package com.stack_labs.workshop

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.Single

@Controller("/nginx")
class NginxController(private val nginxClient: NginxClient) {

    @Get("/")
    fun home(): Single<String> = nginxClient.home()
}