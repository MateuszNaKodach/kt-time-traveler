package com.github.nowakprojects.kttimetraveler.core

import assertk.assert
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.time.Duration
import java.time.LocalTime

const val START_HOUR = 10
const val START_MINUTE = 0

internal class MutableClockSpecification : Spek({

    val startTime: LocalTime by memoized { LocalTime.of(START_HOUR, START_MINUTE) }

    val clock: MutableClock by memoized { MutableClock.withTime(startTime) }

    Feature("advancing time with mutable clock") {

        Scenario("advance time by += operator") {

            Given("clock time is $START_HOUR:$START_MINUTE") {}

            When("clock is advance by 1 minute") {
                clock.advanceBy(Duration.ofMinutes(1))
            }

            Then("clock time is $START_HOUR:${START_MINUTE + 1}"){
                assertThat(LocalTime.from(clock.instant())).isEqualTo(LocalTime.of(START_HOUR, START_MINUTE+1))
            }

        }
    }

})
