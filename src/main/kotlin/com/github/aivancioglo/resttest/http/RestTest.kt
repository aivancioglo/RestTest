package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.setters.Setter
import io.restassured.RestAssured
import io.restassured.config.HttpClientConfig

abstract class RestTest {
    companion object {
        var logIfFailedEnabled = true
            private set

        var logAllRequestsEnabled = false
            private set

        var logOnlyFirstFailure = false
            private set

        private var httpClientConfig = HttpClientConfig.httpClientConfig()

        /**
         * Enable log of all your requests.
         */
        @JvmStatic
        fun enableLogAll() {
            logAllRequestsEnabled = true
        }

        /**
         * Disable log of all failed requests.
         */
        @JvmStatic
        fun disableLogIfFailed() {
            logIfFailedEnabled = false
        }

        /**
         * Disable log of all failures.
         */
        @JvmStatic
        fun enableLogOnlyFirstFailure() {
            logOnlyFirstFailure = true
        }

        /**
         * Set connection timeout.
         */
        @JvmStatic
        fun setConnectionTimeout(timeout: Int) {
            RestAssured.config = RestAssured.config().httpClient(httpClientConfig
                    .setParam("http.connection.timeout", timeout))

        }


         /**
         * Set socket timeout.
         */
        @JvmStatic
        fun setSocketTimeout(timeout: Int) {
            RestAssured.config = RestAssured.config().httpClient(httpClientConfig
                    .setParam("http.socket.timeout", timeout))

        }

        /**
         * Use this function, to creates Session of request repeating.
         */
        @JvmStatic
        fun perform() = Session()

        /**
         * Making GET requestSpecification.
         *
         * @param setters are setting up requestSpecification specification.
         * @return Response instance.
         */
        @JvmStatic
        fun get(vararg setters: Setter) = Endpoint().get(*setters)

        /**
         * Making GET requestSpecification.
         *
         * @param url of your requestSpecification.
         * @param setters are setting up requestSpecification specification.
         * @return Response instance.
         */
        @JvmStatic
        fun get(url: String, vararg setters: Setter) = Endpoint().get(url, *setters)

        /**
         * Making POST requestSpecification.
         *
         * @param setters are setting up requestSpecification specification.
         * @return Response instance.
         */
        @JvmStatic
        fun post(vararg setters: Setter) = Endpoint().post(*setters)

        /**
         * Making POST requestSpecification.
         *
         * @param url of your requestSpecification.
         * @param setters are setting up requestSpecification specification.
         * @return Response instance.
         */
        @JvmStatic
        fun post(url: String, vararg setters: Setter) = Endpoint().post(url, *setters)

        /**
         * Making PUT requestSpecification.
         *
         * @param setters are setting up requestSpecification specification.
         * @return Response instance.
         */
        @JvmStatic
        fun put(vararg setters: Setter) = Endpoint().put(*setters)

        /**
         * Making PUT requestSpecification.
         *
         * @param url of your requestSpecification.
         * @param setters are setting up requestSpecification specification.
         * @return Response instance.
         */
        @JvmStatic
        fun put(url: String, vararg setters: Setter) = Endpoint().put(url, *setters)

        /**
         * Making PATCH requestSpecification.
         *
         * @param setters are setting up requestSpecification specification.
         * @return Response instance.
         */
        @JvmStatic
        fun patch(vararg setters: Setter) = Endpoint().patch(*setters)

        /**
         * Making PATCH requestSpecification.
         *
         * @param url of your requestSpecification.
         * @param setters are setting up requestSpecification specification.
         * @return Response instance.
         */
        @JvmStatic
        fun patch(url: String, vararg setters: Setter) = Endpoint().patch(url, *setters)

        /**
         * Making DELETE requestSpecification.
         *
         * @param setters are setting up requestSpecification specification.
         * @return Response instance.
         */
        @JvmStatic
        fun delete(vararg setters: Setter) = Endpoint().delete(*setters)

        /**
         * Making DELETE requestSpecification.
         *
         * @param url of your requestSpecification.
         * @param setters are setting up requestSpecification specification.
         * @return Response instance.
         */
        @JvmStatic
        fun delete(url: String, vararg setters: Setter) = Endpoint().delete(url, *setters)

        /**
         * Making OPTIONS requestSpecification.
         *
         * @param setters are setting up requestSpecification specification.
         * @return Response instance.
         */
        @JvmStatic
        fun options(vararg setters: Setter) = Endpoint().options(*setters)

        /**
         * Making OPTIONS requestSpecification.
         *
         * @param url of your requestSpecification.
         * @param setters are setting up requestSpecification specification.
         * @return Response instance.
         */
        @JvmStatic
        fun options(url: String, vararg setters: Setter) = Endpoint().options(url, *setters)

        /**
         * Making HEAD requestSpecification.
         *
         * @param setters are setting up requestSpecification specification.
         * @return Response instance.
         */
        @JvmStatic
        fun head(vararg setters: Setter) = Endpoint().head(*setters)

        /**
         * Making HEAD requestSpecification.
         *
         * @param url of your requestSpecification.
         * @param setters are setting up requestSpecification specification.
         * @return Response instance.
         */
        @JvmStatic
        fun head(url: String, vararg setters: Setter) = Endpoint().head(url, *setters)

        /**
         * Making TRACE requestSpecification.
         *
         * @param setters are setting up requestSpecification specification.
         * @return Response instance.
         */
        @JvmStatic
        fun trace(vararg setters: Setter) = Endpoint().trace(*setters)

        /**
         * Making TRACE requestSpecification.
         *
         * @param url of your requestSpecification.
         * @param setters are setting up requestSpecification specification.
         * @return Response instance.
         */
        @JvmStatic
        fun trace(url: String, vararg setters: Setter) = Endpoint().trace(url, *setters)
    }
}