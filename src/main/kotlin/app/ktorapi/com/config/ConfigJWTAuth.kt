package app.ktorapi.com.config

import app.ktorapi.com.model.user.User
import app.ktorapi.com.service.AuthenticateService
import app.ktorapi.com.service.UserService
import app.ktorapi.com.user
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject


fun Application.configJWTAuth() {
    install(Authentication) {
        jwt {
            verifier(AuthenticateService.verifier)
            realm = AuthenticateService.realm
            validate {
                val userInString = it.payload.getClaim("user").asString()
                val mUser = Json.decodeFromString<User?>(userInString)
                if (mUser == null) null
                else {
                    val userService by inject<UserService>()
                    val result = userService.validateLastLogin(mUser.id, it.payload.issuedAt!!)
                    if (result) mUser else null
                }
            }
        }
    }
}