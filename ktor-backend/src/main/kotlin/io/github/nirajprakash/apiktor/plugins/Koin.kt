package io.github.nirajprakash.apiktor.plugins

import io.github.nirajprakash.apiktor.app.appModule
import io.ktor.server.application.*
//import org.koin.ksp.generated.defaultModule
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger() // Logger
        appModule() // Default module with Annotations
        // modules(appModule) // Our module, without dependencies
    }
}