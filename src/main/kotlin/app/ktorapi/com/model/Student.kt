package app.ktorapi.com.model

import app.ktorapi.com.db.AddressTable
import app.ktorapi.com.db.addresses
import app.ktorapi.com.db.db
import kotlinx.serialization.Serializable
import org.ktorm.dsl.eq
import org.ktorm.entity.find

@Serializable
data class Student(
    val id: Int? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val mobile: String? = null,
    val dob: String? = null,
    val sex: String? = null,
    val roleNo: Int? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,

    ) {

    private fun getStudentAddress(): Address? = db.addresses.find { AddressTable.studentId eq this.id!! }

    val address:Address?  = getStudentAddress()
}