package com.github.aivancioglo.resttest.verifiers

import com.github.aivancioglo.resttest.http.Response

class MultiPath(override var key: String, private vararg val paths: Path) : Path(), Verifier {
    override fun verify(response: Response) {
        if (response.rootPath == "") {
            response.root(key)
            paths.forEach { it.verify(response) }
            response.detachRoot(key)
        } else {
            response.appendRoot(".$key")
            paths.forEach { it.verify(response) }
            response.detachRoot(".$key")
        }
    }
}