package com.github.nowakprojects.kttimetraveler.test


import com.github.nowakprojects.kttimetraveler.core.*
import java.time.*

open class TestClockTimeProvider private constructor(clock: Clock) : TimeProvider by ClockTimeProvider(clock) {

    private var _clock: MutableClock = clock.toMutable()

    fun timeTravelTo(localTime: LocalTime) {
        _clock = _clock.apply { atInstant(LocalDate.now(_clock).atTime(localTime).toInstant(_clock.zone)) }
    }

    companion object {

        @JvmOverloads
        @JvmStatic
        fun withFixedTime(localTime: LocalTime, zoneId: ZoneId = ZoneId.systemDefault()): TestClockTimeProvider {
            return TestClockTimeProvider(Clock.fixed(currentInstantWithTime(localTime, zoneId), zoneId))
        }

        fun currentInstantWithTime(localTime: LocalTime, zoneId: ZoneId): Instant {
            return ZonedDateTime.of(LocalDate.now().atTime(localTime), zoneId).toInstant()
        }
    }
}
