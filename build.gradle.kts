import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.30"
    id("eu.leontebbens.gradle.chromedriver-updater") version "1.6.2"
    application
}

group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.30")
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
    testImplementation ("org.mockito:mockito-core:3.8.0")
    testImplementation ("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("org.seleniumhq.selenium:selenium-java:3.14.0")
    testImplementation("org.seleniumhq.selenium:selenium-chrome-driver:3.14.0")
    testImplementation("org.seleniumhq.selenium:selenium-firefox-driver:3.14.0")
    testImplementation("io.github.bonigarcia:webdrivermanager:4.4.0")
}

chromedriver {
    majorVersion = "90"
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClassName = "MainKt"
}