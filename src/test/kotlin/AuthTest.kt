import com.github.aivancioglo.resttest.http.OAuth1.Companion.consumerKey
import com.github.aivancioglo.resttest.http.OAuth1.Companion.consumerSecret
import com.github.aivancioglo.resttest.http.OAuth1.Companion.token
import com.github.aivancioglo.resttest.http.OAuth1.Companion.tokenSecret
import com.github.aivancioglo.resttest.http.Response
import com.github.aivancioglo.resttest.http.RestTest.Companion.get
import com.github.aivancioglo.resttest.verifiers.Verifiers.Companion.body
import org.hamcrest.Matchers.containsString
import org.junit.Test
import java.util.regex.Pattern

class AuthTest {
    private lateinit var response: Response

    @Test
    fun oauth1ValidConsumerKeyAndSecret() {
        response = get("http://term.ie/oauth/example/request_token.php",
                consumerKey("key"),
                consumerSecret("secret"))

        response.assertThat(
                body(containsString("oauth_token")),
                body(containsString("oauth_token_secret")))

    }

    @Test
    fun oauth1InvalidConsumerKey() {
        response = get("http://term.ie/oauth/example/request_token.php",
                consumerKey("invalid"),
                consumerSecret("secret"))

        response.assertThat(
                body(containsString("Invalid consumer")))
    }

    @Test
    fun oauth1InvalidConsumerSecret() {
        response = get("http://term.ie/oauth/example/request_token.php",
                consumerKey("key"),
                consumerSecret("invalid"))

        response.assertThat(
                body(containsString("Invalid signature")))
    }

    @Test
    fun oauth1ValidTokens() {
        response = get("http://term.ie/oauth/example/request_token.php",
                consumerKey("key"),
                consumerSecret("secret"))

        response.assertThat(
                body(containsString("oauth_token")),
                body(containsString("oauth_token_secret")))

        val matcher = Pattern.compile("oauth_token=(.*?)&oauth_token_secret=(.*)").matcher(response.getBody())!!

        matcher.find()

        val token = matcher.group(1)
        val tokenSecret = matcher.group(2)

        response = get("http://term.ie/oauth/example/access_token.php",
                consumerKey("key"),
                consumerSecret("secret"),
                token(token),
                tokenSecret(tokenSecret))

        response.assertThat(
                body(containsString("oauth_token")),
                body(containsString("oauth_token_secret")))
    }

    @Test
    fun oauth1InvalidToken() {
        response = get("http://term.ie/oauth/example/request_token.php",
                consumerKey("key"),
                consumerSecret("secret"))

        response.assertThat(
                body(containsString("oauth_token")),
                body(containsString("oauth_token_secret")))

        val matcher = Pattern.compile("oauth_token=(.*?)&oauth_token_secret=(.*)").matcher(response.getBody())!!

        matcher.find()

        val token = "invalid"
        val tokenSecret = matcher.group(2)

        response = get("http://term.ie/oauth/example/access_token.php",
                consumerKey("key"),
                consumerSecret("secret"),
                token(token),
                tokenSecret(tokenSecret))

        response.assertThat(
                body(containsString("Invalid request token: invalid")))
    }

    @Test
    fun oauth1InvalidTokenSecret() {
        response = get("http://term.ie/oauth/example/request_token.php",
                consumerKey("key"),
                consumerSecret("secret"))

        response.assertThat(
                body(containsString("oauth_token")),
                body(containsString("oauth_token_secret")))

        val matcher = Pattern.compile("oauth_token=(.*?)&oauth_token_secret=(.*)").matcher(response.getBody())!!

        matcher.find()

        val token = matcher.group(1)
        val tokenSecret = "invalid"

        response = get("http://term.ie/oauth/example/access_token.php",
                consumerKey("key"),
                consumerSecret("secret"),
                token(token),
                tokenSecret(tokenSecret))

        response.assertThat(
                body(containsString("Invalid signature")))
    }
}