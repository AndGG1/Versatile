package com.example.chainsearch.feature_register.data.data_source.helpers.utils

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.example.chainsearch.feature_register.data.data_source.helpers.callbacks.CredentialCallback
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun handleCredential(context: Context, req: GetCredentialRequest, callback: CredentialCallback) {
    CoroutineScope(Dispatchers.Main).launch {
        val credentialManager = CredentialManager.create(context)
        try {
            val result = credentialManager.getCredential(
                request = req,
                context = context
            )

            if (result.credential is CustomCredential &&
                result.credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val idToken = obtainToken(result)
                callback.onRes(true, idToken)
            }

        } catch (e: GetCredentialException) {
            callback.onRes(false, null)
        }
    }
}