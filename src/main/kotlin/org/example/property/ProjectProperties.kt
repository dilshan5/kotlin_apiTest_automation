package org.example.property

/**
 * read and store all project properties
 * @author: Dilshan Fernando
 * @since: 22/04/2020
 */
data class ProjectProperties (
    val host: String,
    val client: ClientProperties
)