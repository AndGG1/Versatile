package com.example.chainsearch.initialAction.auth

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.chainsearch.BuildConfig
import de.mkammerer.argon2.Argon2Factory
import java.security.SecureRandom
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

fun convertTo_SHA_256(s: String): String {
    val secretKey = SecretKeySpec(BuildConfig.AUTH_KEY.toByteArray(), "HmacSHA256")
    val mac = Mac.getInstance("HmacSHA256")
    mac.init(secretKey)

    val digest = mac.doFinal(s.toByteArray())
    return digest.fold("") {str, byte -> str + "%02x".format(byte) }
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertTo_Argon2id(s: String) : String {
    val saltKey: String = generateSaltKey(s.length)
    val config = Argon2Factory.create()

    val hash: String = config.hash(
        3,
        65536,
        1,
        s + saltKey
    )
    Log.d("ver+test", hash)
    return hash
}

@RequiresApi(Build.VERSION_CODES.O)
fun generateSaltKey(len: Int) : String {
    val secureRandom: SecureRandom = SecureRandom()
    val bytes: ByteArray = ByteArray(len)
    secureRandom.nextBytes(bytes)

    return Base64.getEncoder().encodeToString(bytes)
}
