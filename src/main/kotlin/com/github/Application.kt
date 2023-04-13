package com.github

import com.github.infra.http.ktor.plugins.configureAmpq
import com.github.infra.http.ktor.plugins.configureDI
import com.github.infra.http.ktor.plugins.configureRouting
import com.github.infra.http.ktor.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>) = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureSerialization()
    configureRouting()
    configureDI()
    configureAmpq()
}
