package app.ktorapi.com.model.respond

import app.ktorapi.com.model.User
import kotlinx.serialization.Serializable

@Serializable class LoginRespond(
    val user : User,
    val token : String
)