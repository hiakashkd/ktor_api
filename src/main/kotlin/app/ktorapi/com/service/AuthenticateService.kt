package app.ktorapi.com.service

import app.ktorapi.com.model.user.User
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.util.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object AuthenticateService {
    private const val validityInMs: Long = 36_000_00 * 24 // 1 day
    private const val issuer = "ktor_api_server"
    private val secret = System.getenv("JWT_SECRET")
    private val algorithm = Algorithm.HMAC512(secret)
    const val realm = "ktor_api"

    private val hashKey = System.getenv("HASH_SECRET_KEY").toByteArray()
    private val hmacKey = SecretKeySpec(hashKey, "HmacSHA1")

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

    fun hash(password: String): String {
        val hmac = Mac.getInstance("HmacSHA1")
        hmac.init(hmacKey)
        return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
    }


    private fun getCurrentTimeMillis(add: Long = validityInMs) =
        Date(System.currentTimeMillis() + add)
}

