import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.30"
    `maven-publish`
}

group = "net.perfectdreams.discordinteraktions"
version = "0.0.2-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://repo.perfectdreams.net")
    }
}

subprojects {
    apply<MavenPublishPlugin>()
    version = "0.0.2-SNAPSHOT"

    publishing {
        repositories {
            maven {
                name = "PerfectDreams"
                url = uri("https://repo.perfectdreams.net/")

                credentials {
                    username = System.getProperty("USERNAME") ?: System.getenv("USERNAME")
                    password = System.getProperty("PASSWORD") ?: System.getenv("PASSWORD")
                }
            }
        }
    }
}