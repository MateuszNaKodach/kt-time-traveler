package pl.zycienakodach.kttimetraveler.core

import java.time.Clock
import java.time.Instant
import java.time.ZoneId

var MutableClock.instant: Instant
    get() = instant()
    set(value) = atInstant(value)

fun Clock.toMutable() = when (this) {
    is MutableClock -> this
    else -> MutableClock(instant(), zone)
}

fun clockFixedAt(instant: Instant): Clock = Clock.fixed(instant, ZoneId.systemDefault())
fun clockFixedNow(zoneId: ZoneId = ZoneId.systemDefault()): Clock = Clock.fixed(Instant.now(), zoneId)
