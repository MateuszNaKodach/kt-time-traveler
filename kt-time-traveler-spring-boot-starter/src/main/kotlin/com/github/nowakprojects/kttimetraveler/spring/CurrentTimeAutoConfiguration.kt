package com.github.nowakprojects.kttimetraveler.spring

import com.github.nowakprojects.kttimetraveler.core.ClockTimeProvider
import com.github.nowakprojects.kttimetraveler.core.TimeProvider
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(CurrentTimeProperties::class)
internal class CurrentTimeAutoConfiguration(private val currentTimeProperties: CurrentTimeProperties) {


    @ConditionalOnMissingBean
    @Bean
    fun getClock() = currentTimeProperties.getClock()

    @ConditionalOnMissingBean
    @Bean
    fun getTimeProvider(): TimeProvider = ClockTimeProvider(getClock())

}
