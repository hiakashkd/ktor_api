package app.ktorapi.com.route

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.homeRoutes() {
    routing {
        authenticate {
            get("/home") {
                call.respondText("home-service")
            }
        }
    }
}