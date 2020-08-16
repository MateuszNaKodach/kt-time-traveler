import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Versions {
    const val ASSERTJ = "3.16.1"
    const val ASSERTK = "0.22"
    const val JUNIT = "5.6.2"
    const val SPEK = "2.0.9"
}

plugins {
    java
    kotlin("jvm")
    id("maven")
    `maven-publish`
}

dependencies {
    implementation(project(":kt-time-traveler-core"))
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.spekframework.spek2", "spek-dsl-jvm", Versions.SPEK)
    testRuntimeOnly("org.spekframework.spek2", "spek-runner-junit5", Versions.SPEK)
    testImplementation("com.willowtreeapps.assertk", "assertk-jvm", Versions.ASSERTK)
    testImplementation("org.junit.jupiter", "junit-jupiter-api", Versions.JUNIT)
    testImplementation("org.junit.jupiter", "junit-jupiter-params", Versions.JUNIT)
    testImplementation("org.junit.jupiter", "junit-jupiter-engine", Versions.JUNIT)
    testImplementation("org.assertj", "assertj-core", Versions.ASSERTJ)
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/nowakprojects/kt-time-traveler")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register("gpr", MavenPublication::class) {
            from(components["java"])
        }
    }
}

tasks.named<Upload>("uploadArchives") {
    repositories.withGroovyBuilder {
        "mavenDeployer" {
            "repository"("url" to "https://maven.pkg.github.com/nowakprojects/kt-time-traveler") {
                "authentication"("userName" to (project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")), "password" to (project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")))
            }
        }
    }
}

