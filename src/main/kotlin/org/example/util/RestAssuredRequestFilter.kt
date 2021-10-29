package org.example.util

import io.restassured.filter.Filter
import io.restassured.filter.FilterContext
import io.restassured.response.Response
import io.restassured.specification.FilterableRequestSpecification
import io.restassured.specification.FilterableResponseSpecification
import org.example.util.CommonConstants.Logging.EXTERNAL_FILE


/**
 * A custom filter to print each request and response to the logger file
 * @author: Dilshan Fernando
 * @since: 27/10/2021
 */
class RestAssuredRequestFilter : Filter {

    override fun filter(
        requestSpec: FilterableRequestSpecification?,
        responseSpec: FilterableResponseSpecification?,
        ctx: FilterContext?
    ): Response {
        val response = ctx!!.next(requestSpec, responseSpec)

        if (requestSpec != null) {
            getLogger(EXTERNAL_FILE)?.info(
                ("------------- Request ---------------" + "\n " +
                        "Request method => " + requestSpec.method + " \n " +
                        "Request URI => " + requestSpec.uri + " \n " +
                        "Request Form params => " + requestSpec.formParams + " \n " +
                        "Request Path params => " + requestSpec.pathParams + " \n " +
                        "Request Query params => " + requestSpec.queryParams + " \n " +
                        "Request Multiparts params => " + requestSpec.multiPartParams + " \n " +
                        "Request Cookies=> " + requestSpec.cookies + " \n " +
                        "Request Headers => " + requestSpec.headers + " \n " +
                        "Request Body =>" + requestSpec.getBody() + "\n " +
                        "------------- Response ---------------" + "\n " +
                        "Response Status => " + response.statusCode + " " + response.statusLine + " \n " +
                        "Response Headers => " + response.headers + " \n " +
                        "Response Body => " + response.body.asPrettyString())
            )
        }
        return response
    }
}