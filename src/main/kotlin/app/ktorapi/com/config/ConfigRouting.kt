package app.ktorapi.com.config

import app.ktorapi.com.route.fakeRoutes
import app.ktorapi.com.route.homeRoutes
import app.ktorapi.com.route.studentRoutes
import app.ktorapi.com.route.userRoutes
import io.ktor.server.application.*

fun Application.configRouting() {
    userRoutes()
    homeRoutes()
    studentRoutes()
    fakeRoutes()
}
