package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.setters.Setter

abstract class RestTest {
    companion object {
        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        @JvmOverloads
        @Deprecated("This method is deprecated", ReplaceWith("repeater()"))
        fun perform(timeout: Int = 0,
                    every: Int = 0,
                    tries: Int = 0) = Repeater().timeout(timeout).every(every).tries(tries)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        @Deprecated("This method is deprecated", ReplaceWith("repeater()"))
        fun perform(timeout: Int = 0,
                    every: Int = 0,
                    tries: Int = 0,
                    request: Runnable) = Repeater().timeout(timeout).every(every).tries(tries).until(request)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        @Deprecated("This method is deprecated", ReplaceWith("repeater()"))
        fun perform(timeout: Int = 0,
                    every: Double = 0.0) = Repeater().timeout(timeout).every(every)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        @Deprecated("This method is deprecated", ReplaceWith("repeater()"))
        fun perform(timeout: Int = 0,
                    request: Runnable) = Repeater().timeout(timeout).until(request)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        @Deprecated("This method is deprecated", ReplaceWith("repeater()"))
        fun perform(timeout: Int = 0,
                    every: Int = 0,
                    request: Runnable) = Repeater().timeout(timeout).every(every).until(request)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        @Deprecated("This method is deprecated", ReplaceWith("repeater()"))
        fun perform(timeout: Int = 0,
                    every: Double = 0.0,
                    request: Runnable) = Repeater().timeout(timeout).every(every).until(request)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        @Deprecated("This method is deprecated", ReplaceWith("repeater()"))
        fun perform(timeout: Int = 0,
                    every: Double = 0.0,
                    tries: Int = 0) = Repeater().timeout(timeout).every(every).tries(tries)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        @Deprecated("This method is deprecated", ReplaceWith("repeater()"))
        fun perform(timeout: Int = 0,
                    every: Double = 0.0,
                    tries: Int = 0,
                    request: Runnable) = Repeater().timeout(timeout).every(every).tries(tries).until(request)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        @Deprecated("This method is deprecated", ReplaceWith("repeater()"))
        fun perform(timeout: Double = 0.0) = Repeater().timeout(timeout)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        @Deprecated("This method is deprecated", ReplaceWith("repeater()"))
        fun perform(timeout: Double = 0.0,
                    request: Runnable) = Repeater().timeout(timeout).until(request)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        @Deprecated("This method is deprecated", ReplaceWith("repeater()"))
        fun perform(timeout: Double = 0.0,
                    every: Int = 0) = Repeater().timeout(timeout).every(every)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        @Deprecated("This method is deprecated", ReplaceWith("repeater()"))
        fun perform(timeout: Double = 0.0,
                    every: Int = 0,
                    request: Runnable) = Repeater().timeout(timeout).every(every).until(request)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        @Deprecated("This method is deprecated", ReplaceWith("repeater()"))
        fun perform(timeout: Double = 0.0,
                    every: Double = 0.0) = Repeater().timeout(timeout).every(every)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        @Deprecated("This method is deprecated", ReplaceWith("repeater()"))
        fun perform(timeout: Double = 0.0,
                    every: Double = 0.0,
                    request: Runnable) = Repeater().timeout(timeout).every(every).until(request)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        @Deprecated("This method is deprecated", ReplaceWith("repeater()"))
        fun perform(timeout: Double = 0.0,
                    every: Double = 0.0,
                    tries: Int = 0) = Repeater().timeout(timeout).every(every).tries(tries)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        @Deprecated("This method is deprecated", ReplaceWith("repeater()"))
        fun perform(timeout: Double = 0.0,
                    every: Double = 0.0,
                    tries: Int = 0,
                    request: Runnable) = Repeater().timeout(timeout).every(every).tries(tries).until(request)
        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        @JvmOverloads
        fun repeat(timeout: Int = 0,
                    every: Int = 0,
                    tries: Int = 0) = Repeater().timeout(timeout).every(every).tries(tries)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        fun repeat(timeout: Int = 0,
                    every: Int = 0,
                    tries: Int = 0,
                    request: Runnable) = Repeater().timeout(timeout).every(every).tries(tries).until(request)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        fun repeat(timeout: Int = 0,
                    every: Double = 0.0) = Repeater().timeout(timeout).every(every)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        fun repeat(timeout: Int = 0,
                    request: Runnable) = Repeater().timeout(timeout).until(request)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        fun repeat(timeout: Int = 0,
                    every: Int = 0,
                    request: Runnable) = Repeater().timeout(timeout).every(every).until(request)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        fun repeat(timeout: Int = 0,
                    every: Double = 0.0,
                    request: Runnable) = Repeater().timeout(timeout).every(every).until(request)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        fun repeat(timeout: Int = 0,
                    every: Double = 0.0,
                    tries: Int = 0) = Repeater().timeout(timeout).every(every).tries(tries)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        fun repeat(timeout: Int = 0,
                    every: Double = 0.0,
                    tries: Int = 0,
                    request: Runnable) = Repeater().timeout(timeout).every(every).tries(tries).until(request)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        fun repeat(timeout: Double = 0.0) = Repeater().timeout(timeout)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        fun repeat(timeout: Double = 0.0,
                    request: Runnable) = Repeater().timeout(timeout).until(request)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        fun repeat(timeout: Double = 0.0,
                    every: Int = 0) = Repeater().timeout(timeout).every(every)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        fun repeat(timeout: Double = 0.0,
                    every: Int = 0,
                    request: Runnable) = Repeater().timeout(timeout).every(every).until(request)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        fun repeat(timeout: Double = 0.0,
                    every: Double = 0.0) = Repeater().timeout(timeout).every(every)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        fun repeat(timeout: Double = 0.0,
                    every: Double = 0.0,
                    request: Runnable) = Repeater().timeout(timeout).every(every).until(request)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        fun repeat(timeout: Double = 0.0,
                    every: Double = 0.0,
                    tries: Int = 0) = Repeater().timeout(timeout).every(every).tries(tries)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        fun repeat(timeout: Double = 0.0,
                    every: Double = 0.0,
                    tries: Int = 0,
                    request: Runnable) = Repeater().timeout(timeout).every(every).tries(tries).until(request)

        /**
         * Use this function, to create Repeater instance for request repeating.
         */
        @JvmStatic
        fun repeat(runnable: Runnable) = Repeater().until(runnable)

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