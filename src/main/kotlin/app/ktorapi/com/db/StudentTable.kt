package app.ktorapi.com.db

import app.ktorapi.com.model.Student
import app.ktorapi.com.model.input.StudentInput
import app.ktorapi.com.uti.toLocalDate
import org.ktorm.dsl.AssignmentsBuilder
import org.ktorm.dsl.QueryRowSet
import org.ktorm.schema.*

object StudentTable : BaseTable<Student>("students") {
    val id = int("id").primaryKey()
    val firstName = varchar("first_name")
    val lastName = varchar("last_name")
    val email = varchar("email")
    val mobile = varchar("mobile")
    val dob = date("dob")
    val sex = varchar("sex")
    val roleNo = int("role_no")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override fun doCreateEntity(row: QueryRowSet, withReferences: Boolean) = Student(
        id = row[id] ?: 0,
        firstName = row[firstName],
        lastName = row[lastName],
        email = row[email],
        mobile = row[mobile],
        dob = row[dob]?.toString(),
        sex = row[sex],
        roleNo = row[roleNo],
        createdAt = row[createdAt].toString(),
        updatedAt = row[updatedAt].toString(),
    )

    fun insert(builder: AssignmentsBuilder, input: StudentInput) {
        builder.run {
            set(firstName, input.firstName)
            set(lastName, input.lastName)
            set(mobile, input.mobile)
            set(email, input.email)
            set(dob, input.dob.toLocalDate())
            set(sex, input.sex)
            set(roleNo, input.roleNo)
        }
    }
}