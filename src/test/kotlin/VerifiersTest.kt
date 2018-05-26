import com.github.aivancioglo.resttest.http.ContentType.JSON
import com.github.aivancioglo.resttest.http.ContentType.XML
import com.github.aivancioglo.resttest.http.Response
import com.github.aivancioglo.resttest.http.RestTest.Companion.get
import com.github.aivancioglo.resttest.http.StatusCode.BAD_REQUEST
import com.github.aivancioglo.resttest.http.StatusCode.OK
import com.github.aivancioglo.resttest.verifiers.Verifiers.Companion.body
import com.github.aivancioglo.resttest.verifiers.Verifiers.Companion.contentTypeIs
import com.github.aivancioglo.resttest.verifiers.Verifiers.Companion.header
import com.github.aivancioglo.resttest.verifiers.Verifiers.Companion.headers
import com.github.aivancioglo.resttest.verifiers.Verifiers.Companion.jsonSchema
import com.github.aivancioglo.resttest.verifiers.Verifiers.Companion.path
import com.github.aivancioglo.resttest.verifiers.Verifiers.Companion.statusCode
import com.github.aivancioglo.resttest.verifiers.Verifiers.Companion.time
import org.hamcrest.Matchers.*
import org.junit.Test
import java.util.concurrent.TimeUnit.MILLISECONDS

class VerifiersTest {
    private lateinit var response: Response

    @Test
    fun statusCodePositive() {
        response = get("httpbin.org/get")

        response.assertThat(
                statusCode(OK))
    }

    @Test(expected = AssertionError::class)
    fun statusCodeNegative() {
        response = get("httpbin.org/get")

        response.assertThat(
                statusCode(BAD_REQUEST))
    }

    @Test
    fun jsonSchemaPositive() {
        response = get("httpbin.org/get")

        response.assertThat(
                jsonSchema("validSchema.json"))
    }

    @Test(expected = AssertionError::class)
    fun jsonSchemaNegative() {
        response = get("httpbin.org/get")

        response.assertThat(
                jsonSchema("invalidSchema.json"))
    }

    @Test
    fun pathPositive() {
        response = get("httpbin.org/get")

        response.assertThat(
                path("headers.Accept", equalTo("*/*")))
    }

    @Test(expected = AssertionError::class)
    fun pathNegative() {
        response = get("httpbin.org/get")

        response.assertThat(
                path("headers.Accept", equalTo("invalid")))
    }

    @Test
    fun bodyPositive() {
        response = get("httpbin.org/get")

        response.assertThat(
                body(containsString("*/*")))
    }

    @Test(expected = AssertionError::class)
    fun bodyNegative() {
        response = get("httpbin.org/get")

        response.assertThat(
                body(containsString("invalid")))
    }

    @Test
    fun contentTypeEnumPositive() {
        response = get("httpbin.org/get")

        response.assertThat(
                contentTypeIs(JSON))
    }

    @Test(expected = AssertionError::class)
    fun contentTypeEnumNegative() {
        response = get("httpbin.org/get")

        response.assertThat(
                contentTypeIs(XML))
    }

    @Test
    fun contentTypeStringPositive() {
        response = get("httpbin.org/get")

        response.assertThat(
                contentTypeIs(JSON.value))
    }

    @Test(expected = AssertionError::class)
    fun contentTypeStringNegative() {
        response = get("httpbin.org/get")

        response.assertThat(
                contentTypeIs(XML.value))
    }

    @Test
    fun headerPositive() {
        response = get("httpbin.org/get")

        response.assertThat(
                header("Content-Type", equalTo("application/json")))
    }

    @Test(expected = AssertionError::class)
    fun headerNegative() {
        response = get("httpbin.org/get")

        response.assertThat(
                header("Content-Type", equalTo("invalid")))
    }

    @Test
    fun headersMapPositive() {
        response = get("httpbin.org/get")

        response.assertThat(
                headers(mapOf("Content-Type" to "application/json")))
    }

    @Test(expected = AssertionError::class)
    fun headersMapNegative() {
        response = get("httpbin.org/get")

        response.assertThat(
                headers(mapOf("Content-Type" to "invalid")))
    }

    @Test
    fun headersSequencePositive() {
        response = get("httpbin.org/get")

        response.assertThat(
                headers("Content-Type", "application/json"))
    }

    @Test(expected = AssertionError::class)
    fun headersSequenceNegative() {
        response = get("httpbin.org/get")

        response.assertThat(
                headers("Content-Type", "invalid"))
    }

    @Test
    fun timePositive() {
        response = get("httpbin.org/get")

        response.assertThat(
                time(greaterThan(0L)))
    }

    @Test(expected = AssertionError::class)
    fun timeMapNegative() {
        response = get("httpbin.org/get")

        response.assertThat(
                time(lessThan(0L)))
    }

    @Test
    fun timeUnitPositive() {
        response = get("httpbin.org/get")

        response.assertThat(
                time(greaterThan(0L), MILLISECONDS))
    }

    @Test(expected = AssertionError::class)
    fun timeUnitMapNegative() {
        response = get("httpbin.org/get")

        response.assertThat(
                time(lessThan(0L), MILLISECONDS))
    }
}