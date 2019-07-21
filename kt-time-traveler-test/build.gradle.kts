import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Versions {
    const val ASSERTJ = "3.12.2"
    const val ASSERTK = "0.17"
    const val JUNIT = "5.3.2"
    const val SPEK = "2.0.5"
}

plugins {
    java
    kotlin("jvm")
    id("maven")
}

group = "com.github.nowakprojects"
version = "0.0.2"

repositories {
    mavenCentral()
    maven {
        url = uri("https://dl.bintray.com/spekframework/spek")
    }
}

dependencies {
    implementation(project(":kt-time-traveler-core"))
    implementation(kotlin("stdlib-jdk8"))
    testCompile("org.spekframework.spek2", "spek-dsl-jvm", Versions.SPEK)
    testRuntime("org.spekframework.spek2", "spek-runner-junit5", Versions.SPEK)
    testCompile("com.willowtreeapps.assertk", "assertk-jvm", Versions.ASSERTK)
    testCompile("org.junit.jupiter", "junit-jupiter-api", Versions.JUNIT)
    testCompile("org.junit.jupiter", "junit-jupiter-params", Versions.JUNIT)
    testCompile("org.junit.jupiter", "junit-jupiter-engine", Versions.JUNIT)
    testCompile("org.assertj", "assertj-core", Versions.ASSERTJ)
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
