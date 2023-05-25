
package com.github.infra.http.ktor.plugins

import com.github.infra.http.ktor.controller.notification.createNotification
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.locations.*

/**
 * This function is an extension function for the Ktor Application class that configures the routing for the application.
 */
fun Application.configureRouting() {

    /**
     * This line installs a Locations feature that allows the application to define routes using annotated classes.
     */
    install(Locations)

    /**
     * This line defines the routing for the application by creating a new routing block.
     */
    routing {

        /**
         * This line adds the createNotification() function to the application's routing tree, so that requests
         * to the "/notifications" endpoint will be handled by that function.
         */
        createNotification()
    }
}

