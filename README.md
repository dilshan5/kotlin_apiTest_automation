# Kotlin-RestAssured Test Automation

All the Test Cases kept in this repository. Developers can select Test Cases which they need to execute during 
their build Pipeline(by adding a tag).

## Running Tests

- Regression tests (excluding wip tests) - `./gradlew regressionTest`
- Developer PipeLine tests (excluding wip tests) - `./gradlew pipeLine1_Test`

- All test cases - `./gradlew test`

## Adding custom test tasks

Custom test tasks can be added in `build.gradle.kts`. 
 
Current test tasks are as follows.
  
- General Regression Test Task (excluding wip tests)  - regressionTest
- Developer PipeLine Test Task (excluding wip tests)  - pipeLine1_Test
