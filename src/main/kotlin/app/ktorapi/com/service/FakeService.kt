package app.ktorapi.com.service

import app.ktorapi.com.data.repository.FakeDataRepository
import app.ktorapi.com.uti.dataRespond
import io.ktor.server.application.*
import io.ktor.server.plugins.*

class FakeService(private val repository: FakeDataRepository) {

    suspend fun fetchLoginNews(call: ApplicationCall) {

        println(call.request.origin.remoteHost)
        val result = repository.fetchSomeFakeLoginNews()
        call.dataRespond(
            data = result
        )
    }
}