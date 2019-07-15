package com.github.nowakprojects.kttimetraveler.core

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.function.Supplier

interface TimeProvider : Supplier<Instant> {

    val localTime: LocalTime

    val localDate: LocalDate

    val instant: Instant

    val localDateTime: LocalDateTime
        get() = LocalDateTime.of(localDate, localTime)

    val timeAtStartOfCurrentDay: LocalDateTime
        get() = localDate.atStartOfDay()

    override fun get(): Instant = instant

}
