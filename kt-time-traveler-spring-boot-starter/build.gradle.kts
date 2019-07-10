import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.spring.gradle.dependencymanagement.DependencyManagementExtension


object Versions {
    const val ASSERTK = "0.17"
    const val SPEK = "2.0.5"
    const val SPRING_BOOT = "2.1.6.RELEASE"
}

plugins {
    java
    kotlin("jvm") version "1.3.41"
    kotlin("plugin.spring") version "1.2.71"
    id("org.springframework.boot") version "2.1.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
}

group = "com.github.nowakprojects"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

configure<DependencyManagementExtension> {
    imports {
        mavenBom("org.springframework.boot:spring-boot-starter-parent:${Versions.SPRING_BOOT}")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation(project(":kt-time-traveler-core"))
    implementation(kotlin("stdlib-jdk8"))

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testCompile("org.springframework.boot:spring-boot-starter-test")
    testCompile("org.spekframework.spek2", "spek-dsl-jvm", Versions.SPEK)
    testRuntime("org.spekframework.spek2", "spek-runner-junit5", Versions.SPEK)
    testCompile("com.willowtreeapps.assertk", "assertk-jvm", Versions.ASSERTK)
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
