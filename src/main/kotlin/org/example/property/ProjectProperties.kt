package org.example.property

/**
 * read and store all project properties
 * @author: Dilshan Fernando
 * @since: 22/11/2020
 */
data class ProjectProperties(
    val host: String,
    val environment: String,
    val release: String,
    val client: ClientProperties
)