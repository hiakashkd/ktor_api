package app.ktorapi.com.db

import app.ktorapi.com.model.user.User
import app.ktorapi.com.model.user.UserInput
import app.ktorapi.com.service.AuthenticateService
import io.ktor.server.auth.*
import kotlinx.serialization.Serializable
import org.ktorm.dsl.AssignmentsBuilder
import org.ktorm.dsl.QueryRowSet
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.datetime
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import java.time.LocalDateTime

interface UserEntity : Entity<UserEntity> {
    companion object : Entity.Factory<UserEntity>()

    var id: Int
    var name: String
    var mobile: String
    var password: String
    var email: String
    var role: String
    var address: String
    var pinCode: String
    var cityId: String
    var lastLoginTime: LocalDateTime
    var createdAt: String
    var updatedAt: String
}


object UserTable : Table<UserEntity>(DBTables.USER) {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val mobile = varchar("mobile").bindTo { it.mobile }
    val password = varchar("password").bindTo { it.password }
    val email = varchar("email").bindTo { it.email }
    val role = varchar("role").bindTo { it.role }
    val address = varchar("address").bindTo { it.address }
    val pinCode = varchar("pincode").bindTo { it.pinCode }
    val cityId = varchar("cityId").bindTo { it.cityId }
    val lastLoginTime = datetime("last_login").bindTo { it.lastLoginTime }
    val createdAt = varchar("created_at").bindTo { it.createdAt }
    val updatedAt = varchar("updated_at").bindTo { it.updatedAt }


    fun toUser(queryRowSet: QueryRowSet): User {
        return User(
            id = queryRowSet[id] ?: 0,
            name = queryRowSet[name].orEmpty(),
            mobile = queryRowSet[mobile].orEmpty(),
            password = queryRowSet[password].orEmpty(),
            email = queryRowSet[email].orEmpty(),
            role = queryRowSet[role].orEmpty(),
            address = queryRowSet[address].orEmpty(),
            pinCode = queryRowSet[pinCode].orEmpty(),
            lastLogin = queryRowSet[lastLoginTime].toString(),
            createdAt = queryRowSet[createdAt].toString(),
            updatedAt = queryRowSet[updatedAt].toString()
        )
    }

    fun insert(builder: AssignmentsBuilder, userInput: UserInput) {
        val hashPassword = AuthenticateService.hash(userInput.password)
        builder.run {
            set(name, userInput.name)
            set(role, userInput.role)
            set(address, userInput.address)
            set(mobile, userInput.mobile)
            set(password, hashPassword)
            set(email, userInput.email)
            set(pinCode, userInput.pincode)
            set(cityId, userInput.cityId)
        }
    }
}