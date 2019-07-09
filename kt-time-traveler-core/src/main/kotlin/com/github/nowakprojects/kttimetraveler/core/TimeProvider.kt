package com.github.nowakprojects.kttimetraveler.core

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface TimeProvider {

    fun getCurrentLocalTime(): LocalTime

    fun getCurrentLocalDate(): LocalDate

    fun getCurrentInstant(): Instant

    fun getCurrentLocalDateTime(): LocalDateTime = LocalDateTime.of(getCurrentLocalDate(), getCurrentLocalTime())

    fun getStartOfCurrentDay(): LocalDateTime = getCurrentLocalDate().atStartOfDay()

}
