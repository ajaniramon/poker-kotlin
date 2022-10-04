import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "dev.rmartinez"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.0-rc1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}