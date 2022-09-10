package app.ktorapi.com.data.repositoryImpl

import app.ktorapi.com.data.repository.AddressRepository
import app.ktorapi.com.db.AddressTable
import app.ktorapi.com.db.db
import app.ktorapi.com.model.input.AddressInput
import org.ktorm.dsl.insert

class AddressRepositoryImpl : AddressRepository {
    override fun insert(input: AddressInput): Boolean {
        return db.insert(AddressTable) { it.insert(this, input) } == 1
    }
}