package com.github.nowakprojects.kttimetraveler.core

import java.time.*

open class ClockTimeProvider(private val clock: Clock) : TimeProvider {

    override fun getCurrentLocalTime(): LocalTime = LocalTime.now(clock)

    override fun getCurrentLocalDate(): LocalDate = LocalDateTime.ofInstant(clock.instant(), clock.zone).toLocalDate()

    override fun getCurrentInstant(): Instant = clock.instant()
}
