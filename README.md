# Kotlin-RestAssured Test Automation

This is a sample project for [Rest Assured with Gradle Kotlin DSL & JUnit 5](https://www.linkedin.com/pulse/rest-assured-gradle-kotlin-dsl-junit-5-dilshan-fernando/?published=t)

## Introduction
All the Test Cases kept in this repository. Developers can select the test cases which they need to execute during 
their build Pipeline(by adding a tag, eg: `pipeLine1`).

## Open the Project

You can open the project by any IDE (Select `build.gradle.kts` file when opening the project)

## Setup the Project

You can set your project configurations in `config.yml`. Please make sure you DO NOT commit sensitive information in this file.
You can pass those data via your CI/CD tools during the execution.

## Continuous Integration/Continuous Deployment  with Jenkins

Here I have used Jenkins as my CI/CD tool. You can edit `Jenkinsfile` based on your requirement.

How to create [Jenkinsfile](https://www.jenkins.io/doc/book/pipeline/jenkinsfile/).

## Running Tests

- Regression tests (excluding wip tests) - `./gradlew regressionTest`
- Developer PipeLine tests (excluding wip tests) - `./gradlew pipeLine1_Test`

- All test cases - `./gradlew test`

## Adding custom test tasks

Custom test tasks can be added in `build.gradle.kts`. 
 
Current test tasks are as follows.
  
- General Regression Test Task (excluding wip tests)  - regressionTest
- Developer PipeLine Test Task (excluding wip tests)  - pipeLine1_Test

# References for learn Kotlin

* https://docs.gradle.org/current/dsl/org.gradle.api.Task.html
* https://openclassrooms.com/en/courses/5774406-learn-kotlin/5931841-enhance-your-classes
* https://kotlinlang.org/docs/reference/classes.html