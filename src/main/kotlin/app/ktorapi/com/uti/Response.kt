package app.ktorapi.com.uti

import kotlinx.serialization.Serializable


@Serializable
class EmptyObject()

typealias EmptyArray = ArrayList<EmptyObject>


sealed class AppResponse<T>() {

    @Serializable
    data class CommonResponse(
        val status: Int,
        val message: String,
    ) : AppResponse<Any>()

    @Serializable
    data class DataRespond<T>(
        val status: Int = 1,
        val message: String = "Success",
        val data: T = EmptyObject() as T,
    ) : AppResponse<T>()


    @Serializable
    data class Validation(
        val status: Int = 101,
        val message: String = "Validation failed!",
        val reasons: List<String>,
    )

}