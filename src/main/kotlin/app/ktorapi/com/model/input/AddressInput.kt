package app.ktorapi.com.model.input

import kotlinx.serialization.Serializable

@Serializable
data class AddressInput(
    val address1: String,
    val address2: String,
    val pinCode: String,
    val city: String,
    val state: String,
    val country: String,
    var studentId: Int? = 0,
)