package app.ktorapi.com.di

import app.ktorapi.com.data.repository.UserRepository
import app.ktorapi.com.service.UserService
import org.koin.dsl.module

object ServiceModule {
    operator fun invoke() = listOf(
        module { single { UserService(get() as UserRepository) } }
    )
}