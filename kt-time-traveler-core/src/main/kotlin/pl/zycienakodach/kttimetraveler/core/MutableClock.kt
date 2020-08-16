package pl.zycienakodach.kttimetraveler.core

import java.time.*
import java.time.temporal.TemporalAmount

class MutableClock(private var instant: Instant, private val zone: ZoneId = ZoneId.systemDefault()) : Clock() {

    companion object {
        @JvmStatic
        fun from(clock: Clock): MutableClock = clock.toMutable()

        @JvmStatic
        fun withTime(instant: Instant): MutableClock = MutableClock(instant)
    }

    override fun withZone(zone: ZoneId): MutableClock = MutableClock(instant, zone)

    override fun getZone(): ZoneId = zone

    override fun instant(): Instant = instant

    fun advanceBy(temporalAmount: TemporalAmount) {
        instant = instant.plus(temporalAmount)
    }

    fun backBy(temporalAmount: TemporalAmount) {
        instant = instant.minus(temporalAmount)
    }

    fun atInstant(instant: Instant) {
        this.instant = instant
    }

    fun toFixed(): Clock = fixed(instant, zone)

    operator fun plusAssign(temporalAmount: TemporalAmount) = advanceBy(temporalAmount)

    operator fun minusAssign(temporalAmount: TemporalAmount) = backBy(temporalAmount)

}
