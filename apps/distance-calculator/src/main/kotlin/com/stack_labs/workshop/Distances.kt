package com.stack_labs.workshop

data class Distances(val distances: List<CityDistance> = emptyList())

data class CityDistance(val from: String, val to: String, val distance: Int)