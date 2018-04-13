import com.github.aivancioglo.resttest.http.RestTest.Companion.get
import com.github.aivancioglo.resttest.http.StatusCode.*
import org.junit.Test

class StatusTest {
    @Test
    fun ok() {
        get("httpbin.org/status/200")
                .assertThat(OK)
    }

    @Test
    fun created() {
        get("httpbin.org/status/201")
                .assertThat(CREATED)
    }

    @Test
    fun accepted() {
        get("httpbin.org/status/202")
                .assertThat(ACCEPTED)
    }

    @Test
    fun noContent() {
        get("httpbin.org/status/204")
                .assertThat(NO_CONTENT)
    }

    @Test
    fun badRequest() {
        get("httpbin.org/status/400")
                .assertThat(BAD_REQUEST)
    }

    @Test
    fun unauthorised() {
        get("httpbin.org/status/401")
                .assertThat(UNAUTHORIZED)
    }

    @Test
    fun forbidden() {
        get("httpbin.org/status/403")
                .assertThat(FORBIDDEN)
    }

    @Test
    fun notFound() {
        get("httpbin.org/status/404")
                .assertThat(NOT_FOUND)
    }

    @Test
    fun methodNotAllowed() {
        get("httpbin.org/status/405")
                .assertThat(METHOD_NOT_ALLOWED)
    }

    @Test
    fun conflict() {
        get("httpbin.org/status/409")
                .assertThat(CONFLICT)
    }
}