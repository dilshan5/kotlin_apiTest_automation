import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.30"
}

group = "org.example"
version = "0.1-SNAPSHOT"

//https://docs.gradle.org/current/dsl/org.gradle.api.artifacts.dsl.RepositoryHandler.html
repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("io.rest-assured:kotlin-extensions:4.4.0")

    implementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.7.2")
    implementation("org.junit.platform:junit-platform-launcher:1.8.0")


    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.5")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.12.5")
    implementation("org.json:json:20210307")
}

tasks.withType<Test> {
    //https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/testing/junitplatform/JUnitPlatformOptions.html
    useJUnitPlatform()
    testLogging.showStandardStreams = true
}

//Defines and configure a new task
tasks.register<Test>("regressionTest") {
    useJUnitPlatform() {
        includeTags("regression")
        excludeTags("wip")
    }
}

tasks.register<Test>("pipeLine1_Test") {
    useJUnitPlatform() {
        includeTags("pipeLine1")
        excludeTags("wip")
    }
}

//Compiles Kotlin source files.
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}