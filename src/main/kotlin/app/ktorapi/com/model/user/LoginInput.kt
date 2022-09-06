package app.ktorapi.com.model.user

import kotlinx.serialization.Serializable

@Serializable data class LoginInput(
    val mobile : String,
    val password : String
)