package org.example.tests.oauth

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.http.HttpStatus
import org.example.model.OAuth
import org.example.model.OAuth.QUERY_PARAM_GRANT_TYPE
import org.example.util.TestBase
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

/**
 * Test cases related to Client credential grant type
 * @author: Dilshan Fernando
 * @since: 22/04/2020
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class ClientCredentialTest: TestBase(){

    companion object {
        private const val QUERY_PARAM_GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials"
        private const val RESPONSE_ACCESS_TOKEN = "access_token"
    }

    @Test
    @Tags(Tag("bvt"), Tag("regression"))
    fun `BSE-2182 - Verify the success response for Base64 encoded method`() {
        Given {
            auth().preemptive().basic(projectProperties.client.id, projectProperties.client.secret)
            queryParam(OAuth.QUERY_PARAM_GRANT_TYPE, QUERY_PARAM_GRANT_TYPE_CLIENT_CREDENTIALS)
        } When {
            post(OAuth.OAUTH_PATH)
        } Then {
            statusCode(HttpStatus.SC_OK)
            body(RESPONSE_ACCESS_TOKEN, CoreMatchers.notNullValue())
        }
    }
}