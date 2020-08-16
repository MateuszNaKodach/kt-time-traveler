import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Versions {
    const val ASSERTK = "0.22"
    const val SPEK = "2.0.9"
    const val SPRING_BOOT = "2.3.1.RELEASE"
}

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("io.spring.dependency-management")
    id("maven")
    `maven-publish`
}

java.sourceCompatibility = JavaVersion.VERSION_1_8

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-autoconfigure:${Versions.SPRING_BOOT}")
    implementation(project(":kt-time-traveler-core"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:${Versions.SPRING_BOOT}")

    testImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.SPRING_BOOT}")
    testImplementation("org.spekframework.spek2", "spek-dsl-jvm", Versions.SPEK)
    testRuntimeOnly("org.spekframework.spek2", "spek-runner-junit5", Versions.SPEK)
    testImplementation("com.willowtreeapps.assertk", "assertk-jvm", Versions.ASSERTK)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
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

