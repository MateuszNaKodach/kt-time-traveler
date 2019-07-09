import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Versions {
    const val SPEK_VERSION = "2.0.5"
}

plugins {
    java
    kotlin("jvm") version "1.3.41"
}

group = "com.github.nowakprojects"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("org.spekframework.spek2", "spek-dsl-jvm", Versions.SPEK_VERSION)
    testRuntime("org.spekframework.spek2", "spek-runner-junit5", Versions.SPEK_VERSION)
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
