package com.github.nowakprojects.kttimetraveler.core

import java.time.Clock
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime

open class ClockTimeProvider(protected var clock: Clock) : TimeProvider {

    override val localTime: LocalTime
        get() = LocalTime.now(clock)

    override val localDate: LocalDate
        get() = LocalDate.now(clock)

    override val instant: Instant
        get() = clock.instant()

    override val zone: ZoneId
        get() = clock.zone
}
