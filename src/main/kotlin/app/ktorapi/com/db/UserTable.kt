package app.ktorapi.com.db

import app.ktorapi.com.model.user.User
import app.ktorapi.com.model.user.UserInput
import org.ktorm.dsl.AssignmentsBuilder
import org.ktorm.dsl.QueryRowSet
import org.ktorm.schema.*

object UserTable : BaseTable<User>(DBConstant.TABLE_USER) {
    val id = int("id")
    val name = varchar("name")
    val mobile = varchar("mobile")
    val password = varchar("password")
    val email = varchar("email")
    val role = varchar("role")
    val address = varchar("address")
    val pinCode = varchar("pincode")
    val cityId = varchar("cityId")
    val lastLoginTime = datetime("last_login")
    val createdAt = varchar("created_at")
    val updatedAt = varchar("updated_at")

    override fun doCreateEntity(row: QueryRowSet, withReferences: Boolean) = User(
        id = row[this.id] ?: 0,
        name = row[this.name],
        email = row[this.email],
        mobile = row[this.mobile],
        password = row[this.password],
        role = row[this.role],
        address = row[this.address],
        pinCode = row[this.pinCode],
        cityId = row[this.cityId],
        lastLoginTime = row[this.lastLoginTime].toString(),
        createdAt = row[this.createdAt],
        updatedAt = row[this.updatedAt],
    )


    fun insert(builder: AssignmentsBuilder, userInput: UserInput) {
        builder.set(name, userInput.name)
        builder.set(mobile, userInput.mobile)
        builder.set(password, userInput.password)
        builder.set(email, userInput.email)
        builder.set(role, userInput.role)
        builder.set(address, userInput.address)
        builder.set(pinCode, userInput.pinCode)
    }

}