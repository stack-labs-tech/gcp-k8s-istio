package com.stack_labs.workshop

import java.util.*

data class Travel(val id: String,
                  val user: String,
                  val startDate: Date,
                  val endDate: Date,
                  val startPlace: String,
                  val endPlace: String,
                  val distanceKm: Int)