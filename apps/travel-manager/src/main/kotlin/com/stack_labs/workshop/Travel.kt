package com.stack_labs.workshop

import java.util.*

data class Travel(var id: String,
                  var user: String,
                  var startDate: Date,
                  var endDate: Date,
                  var startPlace: String,
                  var endPlace: String,
                  var distanceKm: Int)