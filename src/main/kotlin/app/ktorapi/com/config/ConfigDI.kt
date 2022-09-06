package app.ktorapi.com.config

import app.ktorapi.com.di.RepositoryModule
import app.ktorapi.com.di.ServiceModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configDependency() {
    install(Koin) {
        modules(
            listOf(
                RepositoryModule(),
                ServiceModule()
            ).flatten()
        )
    }
}




