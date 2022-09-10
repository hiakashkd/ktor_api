package app.ktorapi.com.model.input

import io.ktor.server.plugins.requestvalidation.*
import kotlinx.serialization.Serializable

@Serializable data class StudentInput(
    val firstName : String,
    val lastName : String,
    val email : String,
    val mobile : String,
    val dob : String,
    val sex : String,
    val roleNo : Int,
    val address : AddressInput
){
    fun validate(): ValidationResult {

        val reasons = arrayListOf<String>()
        if (firstName.length < 3)
            reasons.add("First name minimum 3 characters!")
        if (lastName.length < 3)
            reasons.add("Last name minimum 3 characters!")
        if (email.isEmpty())
            reasons.add("Enter valid email")
        if (mobile.length != 10)
            reasons.add("Enter 10 digits mobile number!")
        if (dob.length != 10)
            reasons.add("Enter valid dob")
        if (!validateSex())
            reasons.add("Enter valid sex!")
        if (roleNo == 0)
            reasons.add("Please provide valid role no")

        return if (reasons.isNotEmpty())
            ValidationResult.Invalid(reasons)
        else ValidationResult.Valid
    }
    private fun validateSex() : Boolean {
        return (sex == "Male" || sex == "Female")
    }
}