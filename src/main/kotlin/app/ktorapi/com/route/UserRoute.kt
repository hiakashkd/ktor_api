package app.ktorapi.com.route

import app.ktorapi.com.service.UserService
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.userRoutes() {
    val userService by inject<UserService>()
    routing {
        post("/login") { userService.login(call) }
        post("/register") { userService.register(call) }
        get("/fetch-users") { userService.fetchUsers(call) }

        authenticate {
            get("/logout") { userService.logout(call) }
        }
    }
}