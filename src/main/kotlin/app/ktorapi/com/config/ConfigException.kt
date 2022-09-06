package app.ktorapi.com.config

import app.ktorapi.com.uti.exceptionRespond
import app.ktorapi.com.uti.validationRespond
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*

fun Application.configException() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            when (cause) {
                is RequestValidationException -> call.validationRespond(reasons = cause.reasons)
              //  else -> call.exceptionRespond(text = cause.message!!)
                else -> throw  cause
            }
        }
    }
}