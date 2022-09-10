package app.ktorapi.com.db

import app.ktorapi.com.model.Address
import app.ktorapi.com.model.input.AddressInput
import org.ktorm.dsl.AssignmentsBuilder
import org.ktorm.dsl.QueryRowSet
import org.ktorm.schema.BaseTable
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object AddressTable : BaseTable<Address>("addresses") {

    val id = int("id").primaryKey()
    val address1 = varchar("address1")
    val address2 = varchar("address2")
    val pinCode = varchar("pinCode")
    val city = varchar("city")
    val state = varchar("state")
    val country = varchar("country")
    val studentId = int("student_id")



    override fun doCreateEntity(row: QueryRowSet, withReferences: Boolean) = Address(
        id = row[id],
        address1 = row[address1],
        address2 = row[address2],
        pinCode = row[pinCode],
        city = row[city],
        state = row[state],
        country = row[country],
        studentId = row[studentId]
    )

    fun insert(assignmentsBuilder: AssignmentsBuilder, input: AddressInput) {
        assignmentsBuilder.run {
            set(address1,input.address1)
            set(address2,input.address2)
            set(pinCode,input.pinCode)
            set(city,input.city)
            set(state,input.state)
            set(country,input.country)
            set(studentId,input.studentId)
        }
    }

}