package ru.rpuxa.bomjonlineserver

import com.sun.net.httpserver.HttpExchange


abstract class Request(val name: String) {
    fun request(exchange: HttpExchange): String {
        val answer = HashMap<String, String>()
        val query = exchange.requestURI.query ?: ""
        val split = query.split('&')
        println(query)
        for (string in split) {
            if ('=' !in string)
                continue
            val (name, value) = string.split('=')
            if (value != NULL)
                answer[name] = value
        }

        return request(Query(answer))
    }

    protected abstract fun request(query: Query): String

    protected class Query(val answer: Map<String, String>) {
        val size get() = answer.size

        operator fun get(string: String) = answer[string]

        fun isEmpty() = answer.isEmpty()
    }

    companion object {
        private const val NULL = "<NULL>"
    }
}
