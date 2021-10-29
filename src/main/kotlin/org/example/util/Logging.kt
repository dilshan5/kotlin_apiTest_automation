package org.example.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * invoke logback library
 * @author: Dilshan Fernando
 * @since: 27/10/2021
 */
fun getLogger(): Logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)
fun getLogger(name: String): Logger? = LoggerFactory.getLogger(name)
