package app.ktorapi.com.data.repositoryImpl

import app.ktorapi.com.data.repository.StudentRepository
import app.ktorapi.com.db.StudentTable
import app.ktorapi.com.db.db
import app.ktorapi.com.db.students
import app.ktorapi.com.model.Student
import app.ktorapi.com.model.input.StudentInput
import org.ktorm.dsl.*
import org.ktorm.entity.find
import org.ktorm.entity.toList

class StudentRepositoryImpl : StudentRepository {
    override fun insert(input: StudentInput): Int? {
        return db.insertAndGenerateKey(StudentTable) { it.insert(this, input) }.toString().toIntOrNull()
    }

    override fun fetchMobileAndEmail(mobile: String, email: String): Pair<String?, String?> {
        val student = db.students
            .find { (StudentTable.mobile) eq mobile and (StudentTable.email eq email) }
        return Pair(student?.mobile, student?.email)

    }

    override fun fetchStudents(): List<Student> {
      return db.students.toList()
    }
}