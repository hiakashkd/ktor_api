package app.ktorapi.com.service

import app.ktorapi.com.data.repository.AddressRepository
import app.ktorapi.com.data.repository.StudentRepository
import app.ktorapi.com.model.input.StudentInput
import app.ktorapi.com.uti.dataRespond
import app.ktorapi.com.uti.failureRespond
import app.ktorapi.com.uti.successRespond
import io.ktor.server.application.*
import io.ktor.server.request.*

class StudentService(
    private val repository: StudentRepository,
    private val addressRepository: AddressRepository,
) {


    suspend fun fetchStudent(call : ApplicationCall){
       val students = repository.fetchStudents()
        call.dataRespond(data = students)
    }

    suspend fun register(call: ApplicationCall) {
        val input = call.receive<StudentInput>()


        //validating mobile and email is already exist
        val isMobileAndEmailValid = validateMobileAndEmail(input)
        if (!isMobileAndEmailValid) {
            call.failureRespond(message = "Mobile and Email is already exists!")
            return
        }
        //insert user
        val generatedKey = repository.insert(input)

        if (generatedKey != null) { //insert address
            input.address.studentId = generatedKey
            val addressResult = addressRepository.insert(input.address)
            if (addressResult) call.successRespond(message = "Data inserted successfully!")
            else call.failureRespond(message = "Unable to insert!!")
        } else call.failureRespond(message = "Unable to insert!")
    }

    private fun validateMobileAndEmail(userInput: StudentInput): Boolean {
        val (mobile: String?, email: String?)
                = repository.fetchMobileAndEmail(userInput.mobile, userInput.email)
        return mobile == null && email == null
    }
}