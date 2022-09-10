package app.ktorapi.com.di

import app.ktorapi.com.data.repository.AddressRepository
import app.ktorapi.com.data.repository.FakeDataRepository
import app.ktorapi.com.data.repository.StudentRepository
import app.ktorapi.com.data.repository.UserRepository
import app.ktorapi.com.service.FakeService
import app.ktorapi.com.service.StudentService
import app.ktorapi.com.service.UserService
import org.koin.dsl.module

object ServiceModule {
    operator fun invoke() = listOf(
        module { single { UserService(get() as UserRepository) } },
        module { single { StudentService(get() as StudentRepository, get() as AddressRepository) } },
        module { single { FakeService(get() as FakeDataRepository) } }
    )
}