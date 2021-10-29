package org.example.util

import org.example.util.CommonConstants.Logging.EXTERNAL_FILE
import org.junit.platform.engine.TestExecutionResult
import org.junit.platform.launcher.TestExecutionListener
import org.junit.platform.launcher.TestIdentifier
import org.junit.platform.launcher.TestPlan

/**
 * This class will listen to the status of the test cases and log to a file
 *
 * @author: Dilshan Fernando
 * @since: 27/10/2021
 */
class Logger : TestExecutionListener {

    var isTestExecuted: Boolean = false
    var isInitializationError: Boolean = true

    override fun executionFinished(testIdentifier: TestIdentifier?, testExecutionResult: TestExecutionResult?) {
        if (testIdentifier != null) {
            if (testIdentifier.type.isTest) {
                if (testExecutionResult != null) {
                    printErrorStack(testExecutionResult)
                    getLogger(EXTERNAL_FILE)?.info(testIdentifier.parentId.toString() + " > " + testIdentifier.displayName.toString() + " : Status: " + testExecutionResult.status.toString())
                }
                getLogger(EXTERNAL_FILE)?.info("---- Ending of test case execution " + testIdentifier.displayName.toString() + " -----")
                isTestExecuted = false
            }

            //log Initialization Errors
            if (!isTestExecuted && isInitializationError) {
                if (testExecutionResult != null) {
                    getLogger(EXTERNAL_FILE)?.error("---- Got Initialization Error in Class > " + testIdentifier.legacyReportingName.toString() + " -------")
                    printErrorStack(testExecutionResult)
                    getLogger(EXTERNAL_FILE)?.info(testIdentifier.displayName.toString() + " > : Status: " + testExecutionResult.status.toString())
                }
                getLogger(EXTERNAL_FILE)?.info("---- Ending of Test Class execution " + testIdentifier.displayName.toString() + " -----")
                isInitializationError = false
            }

        }
    }

    override fun executionSkipped(testIdentifier: TestIdentifier?, reason: String?) {
        if (testIdentifier != null) {
            if (testIdentifier.type.isTest) {
                if (reason != null) {
                    getLogger(EXTERNAL_FILE)?.info(testIdentifier.parentId.toString() + " > " + testIdentifier.displayName.toString() + " : Status: " + reason.toString())
                }
                getLogger(EXTERNAL_FILE)?.info("---- Ending of test case execution " + testIdentifier.displayName.toString() + " -----")
            }
        }
    }

    override fun testPlanExecutionStarted(testPlan: TestPlan?) {
    }

    override fun executionStarted(testIdentifier: TestIdentifier?) {
        if (testIdentifier != null) {
            if (testIdentifier.type.isTest) {
                isTestExecuted = true
                isInitializationError = false
                getLogger(EXTERNAL_FILE)?.info("---- Beginning of test case execution " + testIdentifier.displayName.toString() + " -----")
            }
        }
    }

    private fun printErrorStack(testExecutionResult: TestExecutionResult?) {
        if (testExecutionResult != null) {
            if (testExecutionResult.status.name == "FAILED") {
                getLogger(EXTERNAL_FILE)?.info(
                    "error_message: " + testExecutionResult.throwable.get().stackTraceToString()
                )
            }
        }
    }
}