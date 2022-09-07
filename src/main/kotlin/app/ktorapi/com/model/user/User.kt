package app.ktorapi.com.model.user

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int?,
    val name: String? = null,
    val mobile: String?=null,
    val password: String?=null,
    val email: String?=null,
    val role: String?=null,
    val address: String?=null,
    val pinCode: String?=null,
    val cityId: String?=null,
    val lastLoginTime: String?=null,
    val createdAt: String?=null,
    val updatedAt: String?=null,
) : Principal