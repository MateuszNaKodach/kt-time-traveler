plugins {
    kotlin("jvm") version "1.3.70"
    kotlin("plugin.spring") version "1.3.70"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("maven")
}

allprojects {
    group = "pl.zycienakodach"
    version = "0.1.12"

    repositories {
        mavenCentral()
        maven {
            url = uri("https://dl.bintray.com/spekframework/spek")
        }
    }
}
