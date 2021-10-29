package org.example.util

import org.junit.platform.launcher.TestPlan
import org.junit.platform.launcher.listeners.SummaryGeneratingListener
import java.io.PrintWriter

/**
 * This will print test failure details (including assertions) to console
 *
 * @author Dilshan Fernando
 * @version 1.0
 */
class TestSummaryPrinter : SummaryGeneratingListener() {

    private val writer = PrintWriter(System.out)

    //This method is invoked after all tests in all containers is finished
    override fun testPlanExecutionFinished(testPlan: TestPlan?) {
        super.testPlanExecutionFinished(testPlan)
        printTestSummary()
    }

    private fun printTestSummary() {
        summary.printTo(writer)
    }
}