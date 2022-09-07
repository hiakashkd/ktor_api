package app.ktorapi.com.uti

import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object HashPassword {

    private val hashKey = System.getenv("HASH_SECRET_KEY").toByteArray()
    private val hmacKey = SecretKeySpec(hashKey, "HmacSHA1")

    operator fun invoke(password: String) = hash(password)

    private fun hash(password: String): String {
        val hmac = Mac.getInstance("HmacSHA1")
        hmac.init(hmacKey)
        return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
    }
}