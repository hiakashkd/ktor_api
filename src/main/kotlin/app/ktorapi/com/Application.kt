package app.ktorapi.com

import app.ktorapi.com.config.*
import app.ktorapi.com.db.AppDB
import app.ktorapi.com.model.User
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.doublereceive.*


fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "localhost",
        watchPaths = listOf("classes", "resources")
    ) {
        AppDB()
        configDependency()
        install(ContentNegotiation) { json() }
        install(DoubleReceive)
        install(CallLogging)
        install(CORS){

            allowHost("127.0.0.1:8080")
            anyHost()
            exposedHeaders.add("key")
            allowMethod(HttpMethod.Options)
            allowMethod(HttpMethod.Post)
            allowMethod(HttpMethod.Get)
            allowHeader(HttpHeaders.AccessControlAllowOrigin)
            allowHeader(HttpHeaders.ContentType)



        }
        configJWTAuth()
        configException()
        configValidation()
        configRouting()

    }.start(wait = true)
}

val ApplicationCall.user get() = authentication.principal<User>()