package com.github.nowakprojects.kttimetraveler.core

import java.time.*


fun LocalTime.today() = todayAtZone(ZoneId.systemDefault())
fun LocalTime.todayAtZone(zoneId: ZoneId) = ZonedDateTime.of(LocalDate.now().atTime(this), ZoneId.systemDefault())
