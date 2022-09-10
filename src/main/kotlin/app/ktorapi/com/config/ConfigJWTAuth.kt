package app.ktorapi.com.config

import app.ktorapi.com.model.User
import app.ktorapi.com.service.JWTService
import app.ktorapi.com.service.UserService
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject


fun Application.configJWTAuth() {
    install(Authentication) {
        jwt {
            verifier(JWTService.verifier)
            realm = JWTService.realm
            validate {
                val userInString = it.payload.getClaim("user").asString()
                val mUser = Json.decodeFromString<User?>(userInString)
                val issuedAt = it.payload.issuedAt
                if (mUser == null) null
                else {
                    val userService by inject<UserService>()
                    val result = userService.validateLastLogin(mUser.id!!,issuedAt)
                    if (result) mUser
                    else null
                }
            }
        }
    }
}