package app.ktorapi.com.data.repositoryImpl

import app.ktorapi.com.data.repository.FakeDataRepository
import app.ktorapi.com.model.fake.FakeNewRespond

class FakeDataRepositoryImpl : FakeDataRepository {
    override suspend fun fetchSomeFakeLoginNews(): FakeNewRespond {
        return FakeNewRespond(
            heading = "User login information..",
            subHeading = "Please contact to our customer executive.. User login id and password is reset due " +
                    "to some security issue. In this case you will not able to login with your" +
                    " existing login credential neither you can proceed for forgot password. Please " +
                    "contact our team. Thank you."
        )
    }
}