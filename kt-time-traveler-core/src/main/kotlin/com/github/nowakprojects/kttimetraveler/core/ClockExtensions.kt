package com.github.nowakprojects.kttimetraveler.core

import java.time.Clock
import java.time.Instant

var MutableClock.instant: Instant
    get() = instant()
    set(value) = atInstant(value)

fun Clock.toMutable() = when (this) {
    is MutableClock -> this
    else -> MutableClock(instant(), zone)
}


//TODO: Creating clock with given time
