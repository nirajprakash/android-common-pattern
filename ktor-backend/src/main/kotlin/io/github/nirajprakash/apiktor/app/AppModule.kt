package io.github.nirajprakash.apiktor.app

import io.github.nirajprakash.apiktor.config.AppConfig
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.module

public fun KoinApplication.appModule(): KoinApplication = modules(appModule)
public val appModule : Module = module {
    single() { AppConfig() }

   }