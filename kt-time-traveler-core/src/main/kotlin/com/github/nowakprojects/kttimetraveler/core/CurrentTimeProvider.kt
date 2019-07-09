package com.github.nowakprojects.kttimetraveler.core

import java.time.*

open class CurrentTimeProvider(private val clock: Clock) : TimeProvider {

    override val localTime: LocalTime
        get() = LocalTime.now(clock)

    override val localDate: LocalDate
        get() = LocalDate.now(clock)

    override val instant: Instant
        get() = clock.instant()
}
