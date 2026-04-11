package com.example.chainsearch.initialAction.auth

//
/////
//////////

//THIS CODE IS CURRENTLY POINTLESS.
//WHEN THE API CODE WILL BE IMPLEMENTED,
// THE HASHING FUNCTION(THEN CONVERTED TO SHA-1) WILL HAVE AN USAGE.

//////////
/////
//

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.chainsearch.BuildConfig
import com.lambdapioneer.argon2kt.Argon2Kt
import com.lambdapioneer.argon2kt.Argon2KtResult
import com.lambdapioneer.argon2kt.Argon2Mode
import com.lambdapioneer.argon2kt.Argon2Version
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
fun convertTo_Argon2id(s: String, saltKey: String) : String {
    val argon2k = Argon2Kt()

    val hashRes: Argon2KtResult = argon2k.hash(
        Argon2Mode.ARGON2_ID,
        s.toByteArray(),
        saltKey.toByteArray(),
        5,
        65536,
        1,
        32,
        Argon2Version.V13
    )
    return Base64.getEncoder().encodeToString(hashRes.rawHashAsByteArray())
}

@RequiresApi(Build.VERSION_CODES.O)
fun generateSaltKey(len: Int) : String {
    val secureRandom: SecureRandom = SecureRandom()
    val bytes: ByteArray = ByteArray(len)
    secureRandom.nextBytes(bytes)

    return Base64.getEncoder().encodeToString(bytes)
}
