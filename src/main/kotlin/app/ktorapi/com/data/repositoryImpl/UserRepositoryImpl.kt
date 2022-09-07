package app.ktorapi.com.data.repositoryImpl

import app.ktorapi.com.data.repository.UserRepository
import app.ktorapi.com.db.UserTable
import app.ktorapi.com.db.db
import app.ktorapi.com.db.users
import app.ktorapi.com.model.user.User
import app.ktorapi.com.model.user.UserInput
import app.ktorapi.com.uti.toLocalDate
import org.ktorm.dsl.*
import java.time.LocalDateTime
import java.util.*


class UserRepositoryImpl() : UserRepository {


    override fun fetchLastLoginTime(userId: Int): LocalDateTime? {
        return db.from(UserTable)
            .select(UserTable.lastLoginTime)
            .where(UserTable.id eq userId)
            .map { it[UserTable.lastLoginTime] }
            .firstOrNull()
    }

    override fun saveLastLoginTime(userId: Int, issuedDate: Date): Boolean {
        return db.update(UserTable) {
            set(UserTable.lastLoginTime, issuedDate.toLocalDate())
            where { UserTable.id eq userId }
        } == 1
    }

    override fun fetchUserByMobileAndPassword(mobile: String, password: String): User? {
        return db.from(UserTable)
            .select()
            .where((UserTable.mobile eq mobile) and (UserTable.password eq password))
            .map { UserTable.createEntity(it) }.firstOrNull()
    }

    override fun fetchMobileAndEmail(mobile: String, email: String): Pair<String?, String?> {
        val result = db.from(UserTable).select(UserTable.email, UserTable.mobile)
            .where((UserTable.mobile eq mobile) or (UserTable.email eq email)).map {
                val value1: String? = it[UserTable.mobile]
                val value2: String? = it[UserTable.email]
                Pair(value1, value2)
            }
        return result.firstOrNull() ?: Pair(null, null)
    }

    override fun insert(userInput: UserInput): Boolean {
        return db.insert(UserTable) { UserTable.insert(this, userInput) } == 1
    }

    override fun fetchUsers(): List<User> {
        return db.from(UserTable).select().map { row -> UserTable.createEntity(row) }
    }


}