import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
}

group = "org.example"
version = "0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.rest-assured:kotlin-extensions:4.3.0")

    implementation("org.junit.jupiter:junit-jupiter-api:5.6.1")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.6.1")
    implementation("org.junit.platform:junit-platform-launcher:1.6.2")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.9.2")
    implementation("org.json:json:20190722")
}

tasks.withType<Test> {
    //https://docs.gradle.org/current/javadoc/org/gradle/api/tasks/testing/junitplatform/JUnitPlatformOptions.html
    useJUnitPlatform()
    testLogging.showStandardStreams = true
}

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

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}