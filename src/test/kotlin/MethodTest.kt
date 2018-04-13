import com.github.aivancioglo.resttest.http.Response
import com.github.aivancioglo.resttest.http.RestTest.Companion.delete
import com.github.aivancioglo.resttest.http.RestTest.Companion.get
import com.github.aivancioglo.resttest.http.RestTest.Companion.patch
import com.github.aivancioglo.resttest.http.RestTest.Companion.post
import com.github.aivancioglo.resttest.http.RestTest.Companion.put
import com.github.aivancioglo.resttest.http.StatusCode.OK
import com.github.aivancioglo.resttest.verifiers.Verifiers.Companion.path
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class MethodTest {
    private lateinit var response: Response

    @Test
    fun getRequest() {
        response = get("httpbin.org/get")

        response.assertThat(OK,
                path("headers.Accept", equalTo("*/*")),
                path("headers.Content-Type", equalTo("application/json; charset=UTF-8")))
    }

    @Test
    fun postRequest() {
        response = post("httpbin.org/post")

        response.assertThat(OK,
                path("headers.Accept", equalTo("*/*")),
                path("headers.Content-Type", equalTo("application/json; charset=UTF-8")))
    }

    @Test
    fun putRequest() {
        response = put("httpbin.org/put")

        response.assertThat(OK,
                path("headers.Accept", equalTo("*/*")),
                path("headers.Content-Type", equalTo("application/json; charset=UTF-8")))
    }

    @Test
    fun patchRequest() {
        response = patch("httpbin.org/patch")

        response.assertThat(OK,
                path("headers.Accept", equalTo("*/*")),
                path("headers.Content-Type", equalTo("application/json; charset=UTF-8")))
    }

    @Test
    fun deleteRequest() {
        response = delete("httpbin.org/delete")

        response.assertThat(OK,
                path("headers.Accept", equalTo("*/*")),
                path("headers.Content-Type", equalTo("application/json; charset=UTF-8")))
    }
}