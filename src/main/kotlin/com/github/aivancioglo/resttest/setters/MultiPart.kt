package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest
import java.io.InputStream

/**
 * Specify an inputstream to upload to the server using multi-part form data.
 *
 * @param controlName of the body part. In HTML this is the attribute name of the input tag.
 * @param fileName of the content you're uploading.
 * @param stream you want to requestSpecification.
 * @return RequestSpecification class instance.
 */
class MultiPart<in T : HTTPRequest<*>>(private val controlName : String, private val fileName : String, private val stream : InputStream) : Setter<T> {

    /**
     * Setting body of your requestSpecification.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        request.requestSpecification.multiPart(controlName, fileName, stream)
    }
}