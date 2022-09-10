package app.ktorapi.com.route

import app.ktorapi.com.service.FakeService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.fakeRoutes(){
    val service by inject<FakeService> ()
    routing {
        get("/fetch-user-login-new"){
            service.fetchLoginNews(call)
        }
    }
}