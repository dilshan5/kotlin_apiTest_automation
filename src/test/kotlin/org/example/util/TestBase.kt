package org.example.util

import com.syscolabs.qa.util.ObjectMapperUtil
import io.restassured.RestAssured
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import org.example.property.ProjectProperties
import org.junit.jupiter.api.BeforeAll

abstract class TestBase {
    companion object {

        val projectProperties: ProjectProperties

        init {
            val inputStream = TestBase::class.java.getResourceAsStream("/config.yaml")
            projectProperties = ObjectMapperUtil.yamlObjectMapper
                .readValue(inputStream, ProjectProperties::class.java)
        }

        /**
         * set project configurations
         */
        @BeforeAll
        @JvmStatic
        fun setup() {
            RestAssured.baseURI = "https://${projectProperties.host}"
            RestAssured.filters(RequestLoggingFilter(), ResponseLoggingFilter())
        }


    }
}