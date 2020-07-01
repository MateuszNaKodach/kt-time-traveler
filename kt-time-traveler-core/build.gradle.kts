import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Versions {
    const val ASSERTK = "0.17"
    const val SPEK = "2.0.5"
}

plugins {
    java
    kotlin("jvm")
    id("maven")
}

group = "com.github.nowakprojects"
version = "0.0.3"

repositories {
    mavenCentral()
    maven {
        url = uri("https://dl.bintray.com/spekframework/spek")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

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

/*
TODO: Change to sonatype maven repository publishing
tasks.named<Upload>("uploadArchives") {
    repositories.withGroovyBuilder {
        "mavenDeployer" {
            "repository"("url" to "nexUrl") {
                "authentication"("userName" to "userName", "password" to "password")
            }
        }
    }
}
 */