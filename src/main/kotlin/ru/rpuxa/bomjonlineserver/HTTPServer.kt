package ru.rpuxa.bomjonlineserver

import com.sun.net.httpserver.HttpServer
import ru.rpuxa.bomjonlineserver.db.UserDataBase
import java.net.InetSocketAddress


class HTTPServer : Runnable, AutoCloseable {

    private var server: HttpServer? = null

    override fun run() {
        val server = HttpServer.create(InetSocketAddress(PORT), 0)
        this.server = server
        for (request in RequestCodes.ALL_REQUESTS) {
            server.createContext("/api/${request.name}") {
                val bytes = request.request(it).toByteArray()
                it.sendResponseHeaders(200, bytes.size.toLong())
                val os = it.responseBody
                os.write(bytes)
                os.close()
            }
        }
        server.executor = null
        server.start()
    }

    override fun close() {
        server?.stop(10)
    }

    companion object {
        val PORT = 80

    }
}