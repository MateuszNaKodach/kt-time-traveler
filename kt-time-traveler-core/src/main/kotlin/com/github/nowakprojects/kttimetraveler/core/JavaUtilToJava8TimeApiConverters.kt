package com.github.nowakprojects.kttimetraveler.core

import java.time.*
import java.util.*


fun Date.toLocalDateTime(zoneId: ZoneId = ZoneId.systemDefault()) = LocalDateTime.ofInstant(this.toInstant(), zoneId)
fun Date.toLocalDate(zoneId: ZoneId = ZoneId.systemDefault()) = this.toLocalDateTime(zoneId).toLocalDate()
fun LocalDateTime.toDate(zoneId: ZoneId = ZoneId.systemDefault()) = Date.from(this.atZone(zoneId).toInstant())
fun LocalDate.toDate(zoneId: ZoneId = ZoneId.systemDefault()) = Date.from(this.atStartOfDay().atZone(zoneId).toInstant())
