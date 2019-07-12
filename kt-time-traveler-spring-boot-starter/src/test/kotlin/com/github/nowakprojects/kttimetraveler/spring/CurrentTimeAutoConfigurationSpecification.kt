package com.github.nowakprojects.kttimetraveler.spring

import assertk.assertThat
import assertk.assertions.isNotNull
import com.github.nowakprojects.kttimetraveler.core.TimeProvider
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import java.time.Clock


//https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-developing-auto-configuration.html
internal class CurrentTimeAutoConfigurationSpecification : Spek({

    describe("default current time auto configuration") {

        val contextRunner: ApplicationContextRunner by memoized {
            ApplicationContextRunner()
                    .withConfiguration(AutoConfigurations.of(CurrentTimeAutoConfiguration::class.java))
        }


        it("should provide Clock bean") {
            contextRunner.withUserConfiguration(CurrentTimeProperties::class.java).run { context ->
                val clockBean = context.getBean<Clock>(Clock::javaClass)
                assertThat(clockBean).isNotNull()
            }
        }

        it("should provide TimeProvider bean") {
            contextRunner.withUserConfiguration(CurrentTimeProperties::class.java).run { context ->
                val timeProviderBean = context.getBean<Clock>(TimeProvider::javaClass)
                assertThat(timeProviderBean).isNotNull()
            }
        }

    }

})

