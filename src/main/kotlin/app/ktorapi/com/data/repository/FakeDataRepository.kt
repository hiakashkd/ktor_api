package app.ktorapi.com.data.repository

import app.ktorapi.com.model.fake.FakeNewRespond

interface FakeDataRepository {
    
    suspend fun fetchSomeFakeLoginNews() : FakeNewRespond
}