package ru.rpuxa.bomjonlineserver

import com.sun.net.httpserver.HttpExchange


abstract class Request(val name: String) {
    fun request(exchange: HttpExchange): String {
        val answer = HashMap<String, String>()
        exchange.requestURI.query.split('&').forEach {
            val (name, value) = it.split('=')
            answer[name] = value
        }

        return request(Query(answer))
    }

    protected abstract fun request(query: Query): String

    protected fun Map<String, Any>.toJSON(): String {
        val builder = StringBuilder("{")
        var index = 0
        for ((name, value) in this) {
            val str = when (value) {
                value is Map<*, *> -> toJSON()
                value is Pair<*, *> -> toJSON()
                else -> value.toString()
            }

            builder
                .append('\"')
                .append(name)
                .append("\":")
                .append(str)
            if (index != size - 1)
                builder.append(',')
            index++
        }
        builder.append('}')

        return builder.toString()
    }

    protected fun Pair<String, Any>.toJSON() = mapOf(this).toJSON()

    protected class Query(val answer: Map<String, String>) {
        operator fun get(string: String) = answer[string]
    }

    protected companion object {

    }
}
