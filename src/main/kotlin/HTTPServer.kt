import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress


object HTTPServer {

    @JvmStatic
    fun main(args: Array<String>) {
        val server = HttpServer.create(InetSocketAddress("10.60.8.186", 80), 0)
        server.createContext("/") {
            val builder = StringBuilder()
            for (i in 1..100)
                builder.append(" lox").append('\n')
            val bytes = builder.toString().toByteArray()
            it.sendResponseHeaders(200, bytes.size.toLong())
            val os = it.responseBody
            os.write(bytes)
            os.close()
        }
        server.executor = null
        server.start()
    }
}