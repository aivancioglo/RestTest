import com.github.aivancioglo.resttest.http.ContentType.MULTIPART
import com.github.aivancioglo.resttest.http.ContentType.URLENC
import com.github.aivancioglo.resttest.http.Response
import com.github.aivancioglo.resttest.http.RestTest.Companion.get
import com.github.aivancioglo.resttest.http.RestTest.Companion.post
import com.github.aivancioglo.resttest.http.StatusCode.OK
import com.github.aivancioglo.resttest.setters.Setters.Companion.body
import com.github.aivancioglo.resttest.setters.Setters.Companion.contentType
import com.github.aivancioglo.resttest.setters.Setters.Companion.cookie
import com.github.aivancioglo.resttest.setters.Setters.Companion.formParam
import com.github.aivancioglo.resttest.setters.Setters.Companion.header
import com.github.aivancioglo.resttest.setters.Setters.Companion.host
import com.github.aivancioglo.resttest.setters.Setters.Companion.jsonParam
import com.github.aivancioglo.resttest.setters.Setters.Companion.multiPart
import com.github.aivancioglo.resttest.setters.Setters.Companion.param
import com.github.aivancioglo.resttest.setters.Setters.Companion.path
import com.github.aivancioglo.resttest.setters.Setters.Companion.pathParam
import com.github.aivancioglo.resttest.setters.Setters.Companion.protocol
import com.github.aivancioglo.resttest.setters.Setters.Companion.queryParam
import com.github.aivancioglo.resttest.setters.Setters.Companion.redirects
import com.github.aivancioglo.resttest.setters.Setters.Companion.urlEncodingEnabled
import com.github.aivancioglo.resttest.verifiers.Verifiers.Companion.header
import com.github.aivancioglo.resttest.verifiers.Verifiers.Companion.path
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.equalTo
import org.junit.Ignore
import org.junit.Test
import java.io.File
import java.io.Serializable

class SettersTest {
    private lateinit var response: Response

    @Test
    fun protocol() {
        response = get("httpbin.org/get",
                protocol("https"))

        response.assertThat(
                path("url", containsString("https://")))
    }

    @Test
    fun host() {
        response = get("google.com/get",
                host("httpbin.org"))

        response.assertThat(
                path("url", containsString("httpbin.org")))
    }

    @Test
    fun path() {
        response = get("httpbin.org/post",
                path("/get"))

        response.assertThat(OK)
    }

    @Test
    fun param() {
        response = get("httpbin.org/get",
                param("param", 0))

        response.assertThat(
                path("args.param", equalTo("0")))
    }

    @Test
    fun queryParam() {
        response = get("httpbin.org/get",
                queryParam("queryParam", 0))

        response.assertThat(
                path("args.queryParam", equalTo("0")))
    }

    @Test
    fun formParam() {
        response = get("httpbin.org/get",
                formParam("formParam", 0))

        response.assertThat(
                path("args.formParam", equalTo("0")))
    }

    @Test
    fun jsonParam() {
        response = post("httpbin.org/post",
                jsonParam("jsonParam", 0))

        response.assertThat(
                path("json.jsonParam", equalTo(0)))
    }

    @Test(expected = RuntimeException::class)
    fun jsonParamUrlenc() {
        post("httpbin.org/post",
                contentType(URLENC),
                jsonParam("jsonParam", 0))
    }

    @Test
    fun pathParam() {
        response = get("httpbin.org/{method}",
                pathParam("method", "get"))

        response.assertThat(OK)
    }

    @Ignore("Test in progress")
    @Test
    fun multiPrat() {
        response = post("httpbin.org/post",
                contentType(MULTIPART),
                multiPart("file", "file.txt",
                        File("src/test/resources/file.txt").inputStream()))

        response.assertThat(
                path("args.multiPart", equalTo("video.mp4")))
    }

    @Test
    fun cookie() {
        response = get("httpbin.org/get",
                cookie("name", "value"))

        response.assertThat(
                path("headers.Cookie", equalTo("name=value")))
    }

    @Test
    fun header() {
        response = get("httpbin.org/get",
                header("name", "value"))

        response.assertThat(
                path("headers.Name", equalTo("value")))
    }

    @Test
    fun bodyPayload() {
        class Payload(val param: String) : Serializable

        response = post("httpbin.org/post",
                body(Payload("key")))

        response.assertThat(
                path("data", equalTo("{\"param\":\"key\"}")))
    }

    @Test
    fun bodyString() {
        response = post("httpbin.org/post",
                body("{\"param\":\"key\"}"))

        response.assertThat(
                path("data", equalTo("{\"param\":\"key\"}")))
    }

    @Test
    @Ignore("The HttpClient have a bug. This test can not be automated now.")
    fun redirectsMax() {
        response = get("httpbin.org/redirect/1",
                redirects(0))

        response.assertThat(
                header("Location", equalTo("/get")))
    }

    @Test
    fun redirectsFollow() {
        response = get("httpbin.org/redirect/1",
                redirects(false))

        response.assertThat(
                header("Location", equalTo("/get")))
    }

    @Test(expected = IllegalArgumentException::class)
    fun urlEncodingEnabledFalse() {
        get("httpbin.org/get",
                queryParam("n", " "),
                urlEncodingEnabled(false))
    }

    @Test
    fun urlEncodingEnabledTrue() {
        response = get("httpbin.org/get",
                queryParam("n", " "),
                urlEncodingEnabled(true))

        response.assertThat(
                path("args.n", equalTo(" ")))
    }
}