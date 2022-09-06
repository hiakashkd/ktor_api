package app.ktorapi.com.model.user

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.Date

@Serializable
data class User(
    val id : Int,
    val name: String,
    val mobile: String,
    val password : String,
    val email: String,
    val role: String,
    val address: String,
    val pinCode: String,
    val lastLogin : String,
    val createdAt : String,
    val updatedAt : String
) : Principal