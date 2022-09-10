package app.ktorapi.com.data.repository

import app.ktorapi.com.model.Student
import app.ktorapi.com.model.input.StudentInput

interface StudentRepository {
    fun insert(input: StudentInput): Int?
    fun fetchMobileAndEmail(mobile: String, email: String): Pair<String?, String?>
    fun fetchStudents() : List<Student>
}