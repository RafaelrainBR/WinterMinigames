plugins {
    kotlin("jvm")
}

group = "com.minigames"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api(kotlin("stdlib-jdk8"))
    api(project(":depends"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}