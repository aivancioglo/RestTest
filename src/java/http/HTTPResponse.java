package http;

import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Assertions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class HTTPResponse {
    private Response response;

    public HTTPResponse(Response response) {
        this.response = response;
    }

    public Response lastResponse() {
        return response;
    }

    public static HTTPResponse resultsOf(Response response) {
        return new HTTPResponse(response);
    }

    public HTTPResponse then() {
        return this;
    }

    public Validate assertThat() {
        return new Validate();
    }

    public HTTPResponse log() {
        response.then().log().all();
        return this;
    }

    public HTTPResponse log(boolean isPretty) {
        response.then().log().all(isPretty);
        return this;
    }

    public String asString() {
        return response.getBody().asString();
    }

    public int statusCode() {
        return response.getStatusCode();
    }

    public Response extractResponse() {
        return response.then().extract().response();
    }

    public String extractBody() {
        return response.getBody().asString();
    }

    public <T> T as(Class<T> cls) {
        return response.as(cls);
    }

    public class Validate {
        public HTTPResponse.Validate statusCode(int code) {
            response.then().assertThat().statusCode(code);
            return this;
        }

        public HTTPResponse.Validate validJsonSchema(String schema) {
            response.then().assertThat().body(matchesJsonSchemaInClasspath(schema));
            return this;
        }

        public HTTPResponse.Validate body(Matcher<?> matcher, Matcher<?>... additionalMatchers) {
            response.then().assertThat().body(matcher, additionalMatchers);
            return this;
        }

        public HTTPResponse.Validate path(String path, Matcher<?> matcher, Object... additionalKeyMatcherPairs) {
            response.then().assertThat().body(path, matcher, additionalKeyMatcherPairs);
            return this;
        }

        public HTTPResponse.Validate equals(String expected, String actual) {
            Assertions.assertEquals(expected, actual);
            return this;
        }

        public HTTPResponse.Validate equals(String expected, String actual, String message) {
            Assertions.assertEquals(expected, actual, message);
            return this;
        }

        public HTTPResponse.Validate equals(boolean expected, boolean actual) {
            Assertions.assertEquals(expected, actual);
            return this;
        }

        public HTTPResponse.Validate equals(boolean expected, boolean actual, String message) {
            Assertions.assertEquals(expected, actual, message);
            return this;
        }

        public HTTPResponse.Validate equals(int expected, int actual) {
            Assertions.assertEquals(expected, actual);
            return this;
        }

        public HTTPResponse.Validate equals(int expected, int actual, String message) {

            Assertions.assertEquals(expected, actual, message);
            return this;
        }

        public HTTPResponse.Validate equals(Object expected, Object actual) {
            Assertions.assertEquals(expected, actual);
            return this;
        }

        public HTTPResponse.Validate equals(Object expected, Object actual, String message) {
            Assertions.assertEquals(expected, actual, message);
            return this;
        }

        public HTTPResponse and() {
            return new HTTPResponse(response);
        }
    }
}