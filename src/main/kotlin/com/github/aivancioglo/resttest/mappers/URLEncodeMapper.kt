package com.github.aivancioglo.resttest.mappers

import com.google.gson.Gson
import com.google.gson.JsonElement
import java.util.*


object URLEncodeMapper {
    /**
     * Converts an object to serialized HashMap
     *
     * @param obj object to be serialized
     * @return Serialized URI parameters for use
     */
    fun serialize(obj: Any) = convertTree(Gson().toJsonTree(obj))

    /**
     * Converts the given JSON tree to serialized URI parameters.
     *
     * @param tree The JSON tree (can be an object or array).
     * @return Serialized URI parameters for use
     */
    private fun convertTree(tree: JsonElement): HashMap<String, String> {
        val params = HashMap<String, String>()

        when {
            tree.isJsonArray -> for ((i, element) in tree.asJsonArray.withIndex())
                buildObjectParams("$i", element, params)

            tree.isJsonObject -> for ((key, value) in tree.asJsonObject.entrySet())
                buildObjectParams(key, value, params)

            !tree.isJsonNull -> throw IllegalArgumentException("Cannot convert " + tree.toString())
        }

        return params
    }

    /**
     * Recursive helper method for [.convertTree].
     *
     * @param prefix The prefix for the parameter names.
     * @param tree   The remaining JSON tree.
     * @param params The params object to write to.
     */
    private fun buildObjectParams(prefix: String, tree: JsonElement, params: MutableMap<String, String>) {
        when {
            tree.isJsonArray -> for ((i, element) in tree.asJsonArray.withIndex())
                buildObjectParams("$prefix[$i]", element, params)

            tree.isJsonObject -> for ((key, value) in tree.asJsonObject.entrySet())
                buildObjectParams("$prefix[$key]", value, params)

            tree.isJsonPrimitive -> params[prefix] = tree.asJsonPrimitive.asString
        }
    }
}