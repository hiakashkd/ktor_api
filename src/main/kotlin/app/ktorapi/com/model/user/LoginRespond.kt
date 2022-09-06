package app.ktorapi.com.model.user

import kotlinx.serialization.Serializable

@Serializable class LoginRespond(
    val user : User,
    val token : String
)