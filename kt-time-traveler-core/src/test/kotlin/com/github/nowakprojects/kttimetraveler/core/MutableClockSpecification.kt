package com.github.nowakprojects.kttimetraveler.core

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.time.*

const val START_HOUR = 10
const val START_MINUTE = 30

internal class MutableClockSpecification : Spek({

    Feature("changing time with mutable clock") {

        val startTime: LocalTime by memoized { LocalTime.of(START_HOUR, START_MINUTE) }

        val clock: MutableClock by memoized { MutableClock.withTime(startTime.toInstant()) }

        Scenario("advance time by += operator") {

            Given("clock time is $START_HOUR:$START_MINUTE") {}

            When("clock is advance by 1 minute") {
                clock.instant += Duration.ofMinutes(1)
            }

            Then("clock time is $START_HOUR:${START_MINUTE + 1}") {
                assertThat(LocalTime.now(clock)).isEqualTo(startTime.plusMinutes(1))
            }

        }

        Scenario("back in time by -= operator") {

            Given("clock time is $START_HOUR:$START_MINUTE") {}

            When("clock is back by 1 minute") {
                clock.instant -= Duration.ofMinutes(1)
            }

            Then("clock time is $START_HOUR:${START_MINUTE - 1}") {
                assertThat(LocalTime.now(clock)).isEqualTo(startTime.minusMinutes(1))
            }
        }

    }

    Feature("creating mutable clock from another implementation") {

        Scenario("mutable clock from fixedAt clock") {

            val fixedInstant = Instant.now()
            val fixedClock: Clock by memoized { clockFixedAt(fixedInstant) }

            lateinit var mutableClock: MutableClock

            Given("clock fixed at $fixedInstant") {}

            When("mutable clock is created from fixed one") {
                mutableClock = MutableClock.from(fixedClock)
            }

            Then("mutable clock time is $fixedInstant") {
                assertThat(mutableClock.instant).isEqualTo(fixedInstant)
            }

        }

    }

})
