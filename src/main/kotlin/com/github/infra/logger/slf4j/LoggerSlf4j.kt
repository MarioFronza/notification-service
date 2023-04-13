package com.github.infra.logger.slf4j

import com.github.domain.adapter.logger.ApplicationLogger
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LoggerSlf4j : ApplicationLogger {
    private val loggers = mutableMapOf<String, Logger>()

    private val logger: Logger
        get() = Thread.currentThread().stackTrace[3].className.split(".").first().uppercase().let { module ->
            loggers.getOrPut(module) { LoggerFactory.getLogger(module) }
        }

    override fun info(log: String) {
        logger.info(log)
    }
}