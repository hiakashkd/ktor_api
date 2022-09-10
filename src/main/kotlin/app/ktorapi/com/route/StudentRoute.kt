package app.ktorapi.com.route

import app.ktorapi.com.service.StudentService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.studentRoutes() = routing {
    val studentService by inject<StudentService>()
    post("/student/register") { studentService.register(call) }
    get("/student/fetch-students") { studentService.fetchStudent(call) }
}