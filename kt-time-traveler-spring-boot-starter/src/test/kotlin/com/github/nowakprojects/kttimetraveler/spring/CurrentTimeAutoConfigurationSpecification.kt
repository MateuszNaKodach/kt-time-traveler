package com.github.nowakprojects.kttimetraveler.spring

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner


//https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-developing-auto-configuration.html
internal class CurrentTimeAutoConfigurationSpecification : Spek({

    describe("current time auto configuration") {

        val contextRunner: ApplicationContextRunner by memoized {
            ApplicationContextRunner()
                    .withConfiguration(AutoConfigurations.of())
        }


        it("it") {

        }

    }

})

