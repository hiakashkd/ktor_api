package app.ktorapi.com.service

import app.ktorapi.com.data.repository.UserRepository
import app.ktorapi.com.model.User
import app.ktorapi.com.model.input.LoginInput
import app.ktorapi.com.model.input.UserInput
import app.ktorapi.com.model.respond.LoginRespond
import app.ktorapi.com.user
import app.ktorapi.com.uti.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import java.util.*


class UserService(private val repository: UserRepository) {


    suspend fun login(call: ApplicationCall) {

        val loginInput = call.receive<LoginInput>()
        val hashPassword = HashPassword(loginInput.password)

        val user: User? = repository.fetchUserByMobileAndPassword(loginInput.mobile, hashPassword)
        if (user == null) {
            call.failureRespond(message = "Invalid credentials! user not found")
            return
        }
        val (token, issuedDate) = JWTService.generateToken(user)
        repository.saveLastLoginTime(user.id ?:0, issuedDate)
        call.dataRespond(data = LoginRespond(user, token))
    }


    suspend fun logout(call: ApplicationCall) {
        val user = call.user!!
        JWTService.generateToken(user)
        call.successRespond(message = "Logout successfully!")
    }

    suspend fun register(call: ApplicationCall) = call.withHandler(true) {
        val userInput = call.receive<UserInput>()

        //check mobile and email is exists
        val isMobileAndEmailValid = validateMobileAndEmail(userInput)
        if (!isMobileAndEmailValid) {
            call.failureRespond(message = "Mobile and Email is already exists!")
            return@withHandler
        }
        //insert operation
        val result = repository.insert(userInput)
        if (result) call.successRespond(message = "user inserted successfully!")
        else call.failureRespond(message = "failed to insert user")

    }

    private fun validateMobileAndEmail(userInput: UserInput): Boolean {
        val (mobile: String?, email: String?)
                = repository.fetchMobileAndEmail(userInput.mobile, userInput.email)
        return mobile == null && email == null
    }


    suspend fun fetchUsers(call: ApplicationCall)  {

        val result = repository.fetchUsers()
        val message = if (result.isEmpty())
            "No users found!"
        else "Users found"
        call.dataRespond(message = message, data = result)
    }


    fun validateLastLogin(userId: Int, issuedAt: Date): Boolean {
        val lastLogin = repository.fetchLastLoginTime(userId)
        if (lastLogin == null) return false
        else {

            println("login date 1 : ${lastLogin.toDate()}")
            println("login date 2 : $issuedAt")
            val mLastLoginDate = lastLogin.toDate()
            if (mLastLoginDate.after(issuedAt)) return false
            return true
        }
    }
}