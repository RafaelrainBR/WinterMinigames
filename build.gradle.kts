plugins {
    kotlin("jvm") version "1.3.61"
}

group = "com.minigames"
version = "1.0-SNAPSHOT"

subprojects {
    repositories {
        mavenCentral()
        maven {
            name = "spigot"
            url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
        }
        maven {
            name = "sonatype"
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
        }
    }
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}