package org.example.util

import org.example.property.ProjectProperties

/**
 * Class containing methods to return configuration parameters based on gradle property input
 * If gradle property regarding environment is not input, returning configuration parameters stated in resource config file
 * @author: Dilshan Fernando
 * @since: 20/11/2020
 */
open class ConfigurationSpecification {

    companion object {

        /**
         * Initializing projectProperties to map the configuration parameters from resource config file to
         * return them if the gradle property input not present
         */
        private val projectProperties: ProjectProperties

        init {
            val inputStream = ConfigurationSpecification::class.java.getResourceAsStream("/config.yaml")
            projectProperties = ObjectMapperUtil.yamlObjectMapper.readValue(inputStream, ProjectProperties::class.java)
        }


        /**
         * Methods to Return configuration parameters
         */
        fun getHost(): String {
            return projectProperties.host
        }

        fun getEnvironment(): String {
            return System.getenv("PROFILE")?.toLowerCase() ?: projectProperties.environment
        }

        fun getRelease(): String {
            return System.getenv("RELEASE")?.toLowerCase() ?: projectProperties.release
        }

        fun getClientID(): String {
            return System.getenv("CLIENT_ID") ?: projectProperties.client.id
        }

        fun getClientSecret(): String {
            return System.getenv("CLIENT_SECRET") ?: projectProperties.client.secret
        }
    }
}
