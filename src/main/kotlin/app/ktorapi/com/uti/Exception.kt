package app.ktorapi.com.uti

sealed class AppException(msg: String) : Exception(msg) {

    data class UnableToSaveLogException(val msg: String) : AppException(msg)
    data class SqlInsertionException(val msg: String) : AppException(msg)
}