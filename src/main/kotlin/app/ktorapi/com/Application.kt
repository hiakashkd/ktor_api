package app.ktorapi.com

import app.ktorapi.com.config.*
import app.ktorapi.com.db.AppDB
import app.ktorapi.com.db.UserEntity
import app.ktorapi.com.db.db
import app.ktorapi.com.db.users
import app.ktorapi.com.model.user.User
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.doublereceive.*
import org.ktorm.dsl.eq
import org.ktorm.dsl.notEq
import org.ktorm.entity.add
import org.ktorm.entity.all
import org.ktorm.entity.find
import java.time.LocalDate


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
        configJWTAuth()
        configException()
        configValidation()
        configRouting()

    }.start(wait = true)
}

val ApplicationCall.user get() = authentication.principal<User>()