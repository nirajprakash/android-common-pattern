package io.github.nirajprakash.apiktor

import io.github.nirajprakash.apiktor.plugins.configureKoin
import io.github.nirajprakash.apiktor.plugins.configureRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)


//fun main() {
//    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
//        .start(wait = true)
//}

fun Application.module() {
    configureKoin()
    configureRouting()

}