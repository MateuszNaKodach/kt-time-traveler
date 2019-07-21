package com.github.nowakprojects.kttimetraveler.test

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import org.spekframework.spek2.style.gherkin.ScenarioBody
import java.time.LocalTime

internal class TimeTravelingSpecification : Spek({
    Feature("time traveling with test clock time provider") {

        val timeProvider: TestClockTimeProvider by memoized { TestClockTimeProvider.withFixedTime(LOCAL_TIME_10_00) }

        Scenario("time traveling to the future") {

            `current time is 10h 00m`(timeProvider)

            When("travel in time to 11:00") {
                timeProvider.timeTravelTo(LOCAL_TIME_11_00)
            }

            Then("current time should be 11:00") {
                assertThat(timeProvider.localTime).isEqualTo(LOCAL_TIME_11_00)
            }

        }

        Scenario("time traveling to the past") {

            `current time is 10h 00m`(timeProvider)

            When("travel in time to 9:00") {
                timeProvider.timeTravelTo(LOCAL_TIME_09_00)
            }

            Then("current time should be 09:00") {
                assertThat(timeProvider.localTime).isEqualTo(LOCAL_TIME_09_00)
            }

        }

        Scenario("time traveling to the same time") {

            `current time is 10h 00m`(timeProvider)

            When("no travel in time") {
                timeProvider.timeTravelTo(LOCAL_TIME_10_00)
            }

            Then("current time should be 10:00") {
                assertThat(timeProvider.localTime).isEqualTo(LOCAL_TIME_10_00)
            }

        }

        Scenario("no time traveling") {

            `current time is 10h 00m`(timeProvider)

            When("no travel in time") {}

            Then("current time should be 10:00") {
                assertThat(timeProvider.localTime).isEqualTo(LOCAL_TIME_10_00)
            }

        }


    }

}) {
    companion object {
        val LOCAL_TIME_09_00: LocalTime = LocalTime.of(9, 0)
        val LOCAL_TIME_10_00: LocalTime = LocalTime.of(10, 0)
        val LOCAL_TIME_11_00: LocalTime = LocalTime.of(11, 0)
    }
}

private fun ScenarioBody.`current time is 10h 00m`(timeProvider: TestClockTimeProvider) {
    Given("current time is 10:00") {
        assumeTrue { timeProvider.localTime == TimeTravelingSpecification.LOCAL_TIME_10_00 }
    }
}
