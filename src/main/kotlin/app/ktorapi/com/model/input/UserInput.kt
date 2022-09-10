package app.ktorapi.com.model.input

import io.ktor.server.plugins.requestvalidation.*
import kotlinx.serialization.Serializable

@Serializable data class  UserInput(
    val name : String,
    val mobile : String,
    val password : String,
    val email : String,
    val role : String,
    val address : String,
    val pinCode : String,
    val cityId : String
){
    fun validate(): ValidationResult {

        val reasons = arrayListOf<String>()
        if (name.length < 3)
            reasons.add("Name minimum 3 characters!")
        if (mobile.length != 10)
            reasons.add("Enter 10 digits mobile number!")
        if (password.length < 6)
            reasons.add("Enter min 6 characters password!")
        if (email.isEmpty())
            reasons.add("Enter valid email address!")
        if (address.isEmpty())
            reasons.add("Invalid address!")
        if (role.isEmpty())
            reasons.add("Invalid role!")
        if (pinCode.length != 6)
            reasons.add("Enter 6 digits pin code!")

        return if (reasons.isNotEmpty())
            ValidationResult.Invalid(reasons)
        else ValidationResult.Valid
    }

}