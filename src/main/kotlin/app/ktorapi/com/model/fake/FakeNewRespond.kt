package app.ktorapi.com.model.fake

import kotlinx.serialization.Serializable

@Serializable
data class FakeNewRespond(
    val heading: String,
    val subHeading: String,
)