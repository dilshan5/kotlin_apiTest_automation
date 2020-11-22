package org.example.tests.e2e

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.example.model.JsonPlaceHolder
import org.example.util.CommonConstants
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JsonPlaceHolderTest : JsonPlaceHolder() {

    @Test
    @Tags(Tag(CommonConstants.TestTag.PIPELINE_1), Tag(CommonConstants.TestTag.REGRESSION))
    fun `IDE-1111 - Verify the success response of Post details`() {
        Given {
            setContentTypeHeader("application/json; charset=UTF-8")
        } When {
            get(JsonPlaceHolder.GET_POSTS_RESOURCE_PATH + "5")
        } Then {
            validateResponsePostDetails(5)
        }
    }
}