package com.github.nowakprojects.kttimetraveler.test


import com.github.nowakprojects.kttimetraveler.core.ClockTimeProvider
import com.github.nowakprojects.kttimetraveler.core.TimeProvider
import com.github.nowakprojects.kttimetraveler.core.toInstant
import java.time.*

open class TestClockTimeProvider private constructor(clock: Clock) : ClockTimeProvider(clock), TimeProvider  {

    fun timeTravelTo(localTime: LocalTime) {
        timeTravelTo(localTime.toInstant(localDate, zone))
    }

    fun timeTravelTo(instant: Instant) {
        clock =  Clock.fixed(instant, clock.zone)
    }

    companion object {

        @JvmStatic
        fun withClock(clock: Clock): TestClockTimeProvider {
            return TestClockTimeProvider(clock)
        }

        @JvmOverloads
        @JvmStatic
        fun withFixedTime(localTime: LocalTime, zoneId: ZoneId = ZoneId.systemDefault()): TestClockTimeProvider {
            return TestClockTimeProvider(Clock.fixed(localTime.toInstant(LocalDate.now(), zoneId), zoneId))
        }

        @JvmOverloads
        @JvmStatic
        fun withFixedTime(instant: Instant, zoneId: ZoneId = ZoneId.systemDefault()): TestClockTimeProvider {
            return TestClockTimeProvider(Clock.fixed(instant, zoneId))
        }
    }
}
