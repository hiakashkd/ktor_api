package app.ktorapi.com.config

import app.ktorapi.com.model.user.UserInput
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configValidation() {
    install(RequestValidation) {
        validate<UserInput> { it.validate() }
    }
}
