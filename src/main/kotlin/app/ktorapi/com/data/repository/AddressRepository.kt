package app.ktorapi.com.data.repository

import app.ktorapi.com.model.input.AddressInput

interface AddressRepository {
    fun insert(input : AddressInput) : Boolean
}