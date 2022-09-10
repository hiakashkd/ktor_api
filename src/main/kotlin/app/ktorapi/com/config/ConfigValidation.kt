package app.ktorapi.com.config

import app.ktorapi.com.model.input.StudentInput
import app.ktorapi.com.model.input.UserInput
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configValidation() {
    install(RequestValidation) {
        validate<UserInput> { it.validate() }
        validate<StudentInput>{it.validate()}
    }
}
