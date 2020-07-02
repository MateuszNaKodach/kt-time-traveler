import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Versions {
    const val ASSERTK = "0.22"
    const val SPEK = "2.0.9"
}

plugins {
    java
    kotlin("jvm")
    id("maven")
    `maven-publish`
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("org.spekframework.spek2", "spek-dsl-jvm", Versions.SPEK)
    testRuntimeOnly("org.spekframework.spek2", "spek-runner-junit5", Versions.SPEK)
    testImplementation("com.willowtreeapps.assertk", "assertk-jvm", Versions.ASSERTK)

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
            url = uri("https://maven.pkg.github.com/nowakprojects/kttimetraveler")
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
            "repository"("url" to "https://maven.pkg.github.com/nowakprojects/kttimetraveler") {
                "authentication"("userName" to (project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")), "password" to (project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")))
            }
        }
    }
}

