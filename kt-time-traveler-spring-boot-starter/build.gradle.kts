import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Versions {
    const val ASSERTK = "0.17"
    const val SPEK = "2.0.5"
    const val SPRING_BOOT = "2.1.6.RELEASE"
}

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("io.spring.dependency-management")
}

group = "com.github.nowakprojects"
version = "1.0.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-autoconfigure:${Versions.SPRING_BOOT}")
    implementation(project(":kt-time-traveler-core"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:${Versions.SPRING_BOOT}")

    testCompile("org.springframework.boot:spring-boot-starter-test:${Versions.SPRING_BOOT}")
    testCompile("org.spekframework.spek2", "spek-dsl-jvm", Versions.SPEK)
    testRuntime("org.spekframework.spek2", "spek-runner-junit5", Versions.SPEK)
    testCompile("com.willowtreeapps.assertk", "assertk-jvm", Versions.ASSERTK)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
