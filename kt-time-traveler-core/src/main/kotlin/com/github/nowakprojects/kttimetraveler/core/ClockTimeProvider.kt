package com.github.nowakprojects.kttimetraveler.core

import java.time.*
import java.util.function.Supplier

class ClockTimeProvider(private val clock: Clock) : TimeProvider, Supplier<Instant> {

    override val localTime: LocalTime
        get() = LocalTime.now(clock)

    override val localDate: LocalDate
        get() = LocalDate.now(clock)

    override val instant: Instant
        get() = clock.instant()

    override fun get(): Instant  = instant

}
