package com.github.nowakprojects.kttimetraveler.test

import java.time.LocalTime

interface TimeManipulator {

    fun timeTravelTo(localTime: LocalTime)
}
