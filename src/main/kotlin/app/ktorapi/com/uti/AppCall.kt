package app.ktorapi.com.uti

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

suspend inline fun < reified T> ApplicationCall.dataRespond(status: Int = 1, message: String = "Success", data: T) {
    respond(AppResponse.DataRespond(status, message, data))
}

suspend fun ApplicationCall.successRespond(status: Int = 1, message: String = "Success") {
    respond(AppResponse.CommonResponse(status, message))
}

suspend fun ApplicationCall.exceptionRespond(
    text: String = "Something went wrong",
    status: HttpStatusCode? = HttpStatusCode.InternalServerError,
) {
    respondText(text, status = status)
}

suspend fun ApplicationCall.failureRespond(status: Int = 2, message: String = "Failure") {
    respond(AppResponse.CommonResponse(status, message))
}

suspend fun ApplicationCall.validationRespond(reasons: List<String>) {
    respond(AppResponse.Validation(reasons = reasons))
}