package pl.zycienakodach.kttimetraveler.spring

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.Objects.isNull

@Validated
@ConfigurationProperties(prefix = "timetraveler.current-time")
class CurrentTimeProperties {

    /**
     * Indicate if time should be fixed during application run.
     */
    private var fixed: Boolean = false

    /**
     * Application time zone, by default is system zone.
     * Valid value for Poland is 'Europe/Warsaw'.
     */
    private var zone: ZoneId = ZoneId.systemDefault()

    /**
     * Application date, by default is system date.
     * It should be passed in ISO-8601 (yyyy-MM-dd) format, for example 2019-04-24.
     * If not set current system date is used.
     */
    private var date: LocalDate = LocalDate.now(zone)

    /**
     * Application time, by default is system time.
     * Example values: '"10:15"' or '"10:15:30"' or '"10:15:30.999"'
     * If not set current system time is used.
     */
    private var time: LocalTime = LocalTime.now(zone)


    internal fun getClock(): Clock =
            when (fixed) {
                true -> Clock.fixed(LocalDateTime.of(date, time).atZone(zone).toInstant(), zone)
                false -> Clock.system(zone)
            }


    @Component
    @ConfigurationPropertiesBinding
    private class LocalDateConverter : Converter<String, LocalDate> {
        override fun convert(value: String): LocalDate? =
                if (isNull(value)) null else LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE)

    }

    @Component
    @ConfigurationPropertiesBinding
    private class LocalTimeConverter : Converter<String, LocalTime> {
        override fun convert(value: String): LocalTime? =
                if (isNull(value)) null else LocalTime.parse(value, DateTimeFormatter.ISO_LOCAL_TIME)

    }

    @Component
    @ConfigurationPropertiesBinding
    private class ZoneIdConverter : Converter<String, ZoneId> {
        override fun convert(value: String): ZoneId? =
                if (isNull(value)) null else ZoneId.of(value)

    }

    @Component
    @ConfigurationPropertiesBinding
    private class BooleanConverter : Converter<String, Boolean> {
        override fun convert(value: String): Boolean? =
                when (value.toLowerCase()) {
                    "true" -> true
                    "false" -> false
                    else -> null
                }
    }

}
