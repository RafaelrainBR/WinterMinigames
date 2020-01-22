plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "com.minigames"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    api("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}