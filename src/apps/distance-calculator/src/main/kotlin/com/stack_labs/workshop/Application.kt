package com.stack_labs.workshop

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.stack_labs.workshop")
                .mainClass(Application.javaClass)
                .start()
    }
}