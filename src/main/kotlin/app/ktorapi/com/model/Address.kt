package app.ktorapi.com.model

import app.ktorapi.com.db.StudentTable
import app.ktorapi.com.db.addresses
import app.ktorapi.com.db.db
import app.ktorapi.com.db.students
import kotlinx.serialization.Serializable
import org.ktorm.dsl.eq
import org.ktorm.entity.find

@Serializable data class Address(
    val id: Int? = null,
    val address1: String? = null,
    val address2: String? = null,
    val pinCode: String? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null,
    val studentId: Int? = null,
) {
    fun getStudent(): Student? = db.students.find { StudentTable.id eq this.studentId!! }
}