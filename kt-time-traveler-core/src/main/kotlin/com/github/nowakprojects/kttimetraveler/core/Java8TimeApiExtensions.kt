package com.github.nowakprojects.kttimetraveler.core

import java.time.*

fun LocalTime.toInstant(localDate: LocalDate = LocalDate.now(), zoneId: ZoneId = ZoneId.systemDefault()) =
        this.atDate(localDate).toInstant(zoneId)

fun LocalDate.toInstant(localTime: LocalTime = LocalTime.of(0, 0), zoneId: ZoneId = ZoneId.systemDefault()) =
        this.atTime(localTime).atZone(zoneId).toInstant()

fun LocalDateTime.toInstant(zoneId: ZoneId = ZoneId.systemDefault()) =
        this.atZone(zoneId).toInstant()

fun Instant.toLocalDateTime(zoneId: ZoneId = ZoneId.systemDefault()) =
        LocalDateTime.ofInstant(this, zoneId)

fun LocalTime.today() =
        todayAtZone(ZoneId.systemDefault())

fun LocalTime.todayAtZone(zoneId: ZoneId) =
        ZonedDateTime.of(LocalDate.now().atTime(this), zoneId)
