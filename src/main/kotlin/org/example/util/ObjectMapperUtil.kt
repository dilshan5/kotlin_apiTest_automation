package com.syscolabs.qa.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

object ObjectMapperUtil {

    val yamlObjectMapper = ObjectMapper(YAMLFactory())
        .registerKotlinModule()

}
