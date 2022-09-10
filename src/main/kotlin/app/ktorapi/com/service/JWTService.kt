package app.ktorapi.com.service

import app.ktorapi.com.model.User
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*

object JWTService {
    private const val validityInMs: Long = 36_000_00 * 24 // 1 day
    private const val issuer = "ktor_api_server"
    private val secret = System.getenv("JWT_SECRET")
    private val algorithm = Algorithm.HMAC512(secret)
    const val realm = "ktor_api"


    val verifier = JWT.require(algorithm).withIssuer(issuer).build()!!

    fun generateToken(user: User): Pair<String, Date> {
        val issuedDate = getCurrentTimeMillis(0)
        val expiryDate = getCurrentTimeMillis(validityInMs)
        val token = generateJwtToken(user, expiryDate, issuedDate)!!
        return Pair(token, issuedDate)
    }

    private fun generateJwtToken(
        user: User,
        expiryDate: Date,
        issuedAt: Date,
    ): String? {

        println("mDateCheck : issuedAt -> $issuedAt")
        println("mDateCheck : expiryAt -> $expiryDate")

        val encodeUser = Json.encodeToString(user)
        return JWT
            .create()
            .withSubject("ApiAuthentication")
            .withIssuer(issuer)
            .withClaim("user", encodeUser)
            .withExpiresAt(expiryDate)
            .withIssuedAt(issuedAt)
            .sign(algorithm)
    }



    private fun getCurrentTimeMillis(add: Long) =
        Date(System.currentTimeMillis() + add)
}

