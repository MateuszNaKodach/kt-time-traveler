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
    signing
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

java {
    withJavadocJar()
    withSourcesJar()
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
        maven {
            name = "MavenCentral"
            val releasesStagingRepoUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2")
            val snapshotsRepoUrl = uri("https://oss.sonatype.org/content/repositories/snapshots")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesStagingRepoUrl
            credentials {
                username = System.getenv("MAVEN_CENTRAL_USERNAME")
                password = System.getenv("MAVEN_CENTRAL_PASSWORD")
            }
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "kt-time-traveler-core"
            from(components["java"])
            pom {
                name.set("Kt Time Traveler")
                description.set("Single source of truth for the time in your application.")
                url.set("https://github.com/nowakprojects/kt-time-traveler")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("nowakprojects")
                        name.set("Mateusz Nowak")
                        email.set("kontakt.mateusznowak@gmail.com")
                        url.set("https://zycienakodach.pl")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/nowakprojects/kt-time-traveler.git")
                    developerConnection.set("scm:git:ssh://github.com/nowakprojects/kt-time-traveler.git")
                    url.set("https://github.com/nowakprojects/kt-time-traveler/")
                }
            }
        }
    }
}

signing {
    val pgpSigningKey = System.getenv("PGP_SIGNING_KEY")
    val pgpSigningPassword = System.getenv("PGP_SIGNING_PASSWORD")
    useInMemoryPgpKeys(pgpSigningKey, pgpSigningPassword)
    sign(publishing.publications["mavenJava"])
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
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

