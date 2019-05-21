package com.stack_labs.workshop

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single

@Client("\${nginx.url}")
interface NginxClient {

    @Get("/")
    fun home(): Single<String>
}