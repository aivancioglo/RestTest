package resttest;

import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Assertions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * This class is using for HTTP/HTTPS response validation and processing.
 */
public class HTTPResponse {
    private Response response;

    public HTTPResponse(Response response) {
        this.response = response;
    }

    /**
     * Method is returning instance of Response (Rest Assured).
     * @return Response instance.
     */
    public Response lastResponse() {
        return response;
    }

    /**
     * Static method is returning instance of this class.
     * @param response Response instance of Rest Assured.
     * @return new HTTPRequest instance.
     */
    public static HTTPResponse resultsOf(Response response) {
        return new HTTPResponse(response);
    }

    /**
     * Returns instance of this class.
     * @return this class instance.
     */
    public HTTPResponse then() {
        return this;
    }

    /**
     * Returns instance of Validate class.
     * @return Validate instance.
     */
    public Validate assertThat() {
        return new Validate();
    }

    /**
     * Use this method for response logging.
     * @return this class instance.
     */
    public HTTPResponse log() {
        response.then().log().all();
        return this;
    }

    /**
     * Use this method for response logging.
     * @param isPretty is setting if log is pretty.
     * @return this class instance.
     */
    public HTTPResponse log(boolean isPretty) {
        response.then().log().all(isPretty);
        return this;
    }

    /**
     * Returns response code of last response.
     * @return response code.
     */
    public int statusCode() {
        return response.getStatusCode();
    }

    /**
     * Returns instance of Response (Rest Assured).
     * @return instance of Response.
     */
    public Response extractResponse() {
        return response.then().extract().response();
    }

    /**
     * Returns body of last response as string.
     * @return body as string.
     */
    public String extractBody() {
        return response.getBody().asString();
    }

    /**
     * Deserialize response body as java class.
     * @param cls java class.
     * @param <T> response model.
     * @return deserialized body as java class.
     */
    public <T> T as(Class<T> cls) {
        return response.as(cls);
    }

    /**
     * Class for response validation and assertions.
     */
    public class Validate {

        /**
         * Assert response status code.
         * @param code response status code.
         * @return this class instance.
         */
        public HTTPResponse.Validate statusCode(int code) {
            response.then().assertThat().statusCode(code);
            return this;
        }

        /**
         * Validate response body using JSON schema.
         * @param schema JSON schema.
         * @return this class instance.
         */
        public HTTPResponse.Validate validJsonSchema(String schema) {
            response.then().assertThat().body(matchesJsonSchemaInClasspath(schema));
            return this;
        }

        /**
         * Assert response body using Hamcrest asserts.
         * @param matcher class of Hamcrest.
         * @param additionalMatchers array of matchers.
         * @return this class instance.
         */
        public HTTPResponse.Validate body(Matcher<?> matcher, Matcher<?>... additionalMatchers) {
            response.then().assertThat().body(matcher, additionalMatchers);
            return this;
        }

        /**
         * Assert by path using matchers and Rest Assured.
         * @param path JSON path.
         * @param matcher class of Hamcrest.
         * @param additionalKeyMatcherPairs array of matchers.
         * @return this class instance.
         */
        public HTTPResponse.Validate path(String path, Matcher<?> matcher, Object... additionalKeyMatcherPairs) {
            response.then().assertThat().body(path, matcher, additionalKeyMatcherPairs);
            return this;
        }

        /**
         * Assert equals using JUnit 5 assert.
         * @param expected string.
         * @param actual string.
         * @return this class instance.
         */
        public HTTPResponse.Validate equals(String expected, String actual) {
            Assertions.assertEquals(expected, actual);
            return this;
        }

        /**
         * Assert equals using JUnit 5 assert.
         * @param expected string.
         * @param actual string.
         * @param message fail message.
         * @return this class instance.
         */
        public HTTPResponse.Validate equals(String expected, String actual, String message) {
            Assertions.assertEquals(expected, actual, message);
            return this;
        }

        /**
         * Assert equals using JUnit 5 assert.
         * @param expected boolean.
         * @param actual boolean.
         * @return this class instance.
         */
        public HTTPResponse.Validate equals(boolean expected, boolean actual) {
            Assertions.assertEquals(expected, actual);
            return this;
        }

        /**
         * Assert equals using JUnit 5 assert.
         * @param expected boolean.
         * @param actual boolean.
         * @param message fail message.
         * @return this class instance.
         */
        public HTTPResponse.Validate equals(boolean expected, boolean actual, String message) {
            Assertions.assertEquals(expected, actual, message);
            return this;
        }

        /**
         * Assert equals using JUnit 5 assert.
         * @param expected int.
         * @param actual int.
         * @return this class instance.
         */
        public HTTPResponse.Validate equals(int expected, int actual) {
            Assertions.assertEquals(expected, actual);
            return this;
        }

        /**
         * Assert equals using JUnit 5 assert.
         * @param expected int.
         * @param actual int.
         * @param message fail message.
         * @return this class instance.
         */
        public HTTPResponse.Validate equals(int expected, int actual, String message) {

            Assertions.assertEquals(expected, actual, message);
            return this;
        }

        /**
         * Assert equals using JUnit 5 assert.
         * @param expected Object.
         * @param actual Object.
         * @return this class instance.
         */
        public HTTPResponse.Validate equals(Object expected, Object actual) {
            Assertions.assertEquals(expected, actual);
            return this;
        }

        /**
         * Assert equals using JUnit 5 assert.
         * @param expected Object.
         * @param actual Object.
         * @param message fail message.
         * @return this class instance.
         */
        public HTTPResponse.Validate equals(Object expected, Object actual, String message) {
            Assertions.assertEquals(expected, actual, message);
            return this;
        }

        /**
         * Returns HTTPResponse instance.
         * @return the new HTTPResponse instance.
         */
        public HTTPResponse and() {
            return new HTTPResponse(response);
        }
    }
}