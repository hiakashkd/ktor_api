package app.ktorapi.com.db

import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf


val Database.users get() = this.sequenceOf(UserTable)
val Database.students get() = this.sequenceOf(StudentTable)
val Database.addresses get() = this.sequenceOf(AddressTable)

object AppDB {
    lateinit var instance: Database
    operator fun invoke(): Database {
        return if (!this::instance.isInitialized) {
            instance = init()
            instance
        } else instance
    }

    private fun init(): Database {
        return Database.connect(
            url = "jdbc:mysql://localhost:3306/school_management",
            driver = "com.mysql.jdbc.Driver",
            user = "root",
            password = "Akash@123"
        )
    }
}
val db = AppDB()