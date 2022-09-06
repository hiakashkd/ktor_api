package app.ktorapi.com.di

import app.ktorapi.com.data.repository.UserRepository
import app.ktorapi.com.data.repositoryImpl.UserRepositoryImpl
import org.koin.dsl.module

object RepositoryModule {
    operator fun invoke() = listOf(
        module { single { UserRepositoryImpl() as UserRepository } }
    )
}