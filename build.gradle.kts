plugins {
    kotlin("jvm") version "1.3.70"
    kotlin("plugin.spring") version "1.3.70"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    id("maven")
}

allprojects {
    group = "com.github.nowakprojects"
    version = "0.0.4"
}