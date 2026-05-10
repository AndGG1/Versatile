package com.example.chainsearch.feature_register.data.data_source.helpers.utils

import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

fun obtainToken(credentialObj: GetCredentialResponse): String {
    val googleIdTokeWrapped =
        GoogleIdTokenCredential.createFrom(credentialObj.credential.data)
    return googleIdTokeWrapped.idToken
}