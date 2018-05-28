import com.github.aivancioglo.resttest.http.ContentType.*
import com.github.aivancioglo.resttest.http.Response
import com.github.aivancioglo.resttest.http.RestTest.Companion.get
import com.github.aivancioglo.resttest.http.StatusCode.OK
import com.github.aivancioglo.resttest.setters.Setters.Companion.accept
import com.github.aivancioglo.resttest.setters.Setters.Companion.contentType
import com.github.aivancioglo.resttest.verifiers.Verifiers.Companion.path
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class ContentTypeTest {
    private lateinit var response: Response

    @Test
    fun anyRequest() {
        response = get("httpbin.org/get",
                contentType(ANY),
                accept(ANY))

        response.assertThat(OK,
                path("headers.Accept", equalTo(ANY.value)),
                path("headers.Content-Type", containsString("*/*")))
    }

    @Test
    fun jsonRequest() {
        response = get("httpbin.org/get",
                contentType(JSON),
                accept(JSON))

        response.assertThat(OK,
                path("headers.Accept", equalTo(JSON.value)),
                path("headers.Content-Type", containsString("application/json")))
    }

    @Test
    fun urlencRequest() {
        response = get("httpbin.org/get",
                contentType(URLENC),
                accept(URLENC))

        response.assertThat(OK,
                path("headers.Accept", equalTo(URLENC.value)),
                path("headers.Content-Type", containsString("application/x-www-form-urlencoded")))
    }

    @Test
    fun xmlRequest() {
        response = get("httpbin.org/get",
                contentType(XML),
                accept(XML))

        response.assertThat(OK,
                path("headers.Accept", equalTo(XML.value)),
                path("headers.Content-Type", containsString("application/xml")))
    }

    @Test
    fun textRequest() {
        response = get("httpbin.org/get",
                contentType(TEXT),
                accept(TEXT))

        response.assertThat(OK,
                path("headers.Accept", equalTo(TEXT.value)),
                path("headers.Content-Type", containsString("text/plain")))
    }

    @Test
    fun binaryRequest() {
        response = get("httpbin.org/get",
                contentType(BINARY),
                accept(BINARY))

        response.assertThat(OK,
                path("headers.Accept", equalTo(BINARY.value)),
                path("headers.Content-Type", containsString("application/octet-stream")))
    }

    @Test
    fun contentTypeWithCharsetRequest() {
        response = get("httpbin.org/get",
                contentType("application/json; charset=UTF-16"),
                accept(JSON))

        response.assertThat(OK,
                path("headers.Accept", equalTo(JSON.value)),
                path("headers.Content-Type", containsString("application/json; charset=UTF-16")))
    }
}