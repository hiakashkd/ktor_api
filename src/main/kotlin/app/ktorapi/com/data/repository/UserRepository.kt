package app.ktorapi.com.data.repository

import app.ktorapi.com.model.user.User
import app.ktorapi.com.model.user.UserInput
import java.time.LocalDateTime
import java.util.Date

interface UserRepository {
    fun fetchLastLoginTime(userId: Int): LocalDateTime?
    fun saveLastLoginTime(userId: Int, issuedDate: Date): Boolean
    fun fetchUserByMobileAndPassword(mobile: String, password: String): User?
    fun fetchMobileAndEmail(mobile: String, email: String): Pair<String?, String?>
    fun insert(userInput: UserInput): Boolean
    fun fetchUsers(): List<User>
}