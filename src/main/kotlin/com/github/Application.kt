package com.github

import io.ktor.server.application.*
import com.github.plugins.*
import io.ktor.server.netty.*

fun main(args: Array<String>) = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureSerialization()
    configureRouting()
}
