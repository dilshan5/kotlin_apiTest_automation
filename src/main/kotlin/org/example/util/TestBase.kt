package org.example.util

import io.restassured.RestAssured
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll

/**
 * Base class for the Test classes
 * @author: Dilshan Fernando
 * @since: 22/11/2020
 */
abstract class TestBase {

    companion object {

        /**
         * set project configurations
         */
        @BeforeAll
        @JvmStatic
        fun setup() {
            RestAssured.baseURI = "https://${ConfigurationSpecification.getHost()}"
            //print the request specification details.
            //print the response details if the response matches a given status code.
            RestAssured.filters(RequestLoggingFilter(), ResponseLoggingFilter())
        }

        /**
         * Include the cleanup code
         */
        @AfterAll
        @JvmStatic
        fun cleanUp() {

        }

    }
}