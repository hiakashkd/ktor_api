package app.ktorapi.com.di

import app.ktorapi.com.data.repository.AddressRepository
import app.ktorapi.com.data.repository.FakeDataRepository
import app.ktorapi.com.data.repository.StudentRepository
import app.ktorapi.com.data.repository.UserRepository
import app.ktorapi.com.data.repositoryImpl.AddressRepositoryImpl
import app.ktorapi.com.data.repositoryImpl.FakeDataRepositoryImpl
import app.ktorapi.com.data.repositoryImpl.StudentRepositoryImpl
import app.ktorapi.com.data.repositoryImpl.UserRepositoryImpl
import org.koin.dsl.module

object RepositoryModule {
    operator fun invoke() = listOf(
        module { single { UserRepositoryImpl() as UserRepository } },
        module { single { StudentRepositoryImpl() as StudentRepository } },
        module { single { AddressRepositoryImpl() as AddressRepository } },
        module { single { FakeDataRepositoryImpl() as FakeDataRepository } },
    )
}