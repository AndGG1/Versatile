package com.example.chainsearch.feature_register.data.data_source.basicRegistration

import com.example.chainsearch.feature_register.data.data_source.helpers.callbacks.IsValidCallback
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val mAuth = FirebaseAuth.getInstance()
private val databaseRef = FirebaseDatabase.getInstance(
    "https://versatile-bf2d4-default-rtdb.europe-west1.firebasedatabase.app/"
).reference

fun callRegisterEmail(username: String, password: String, email: String, callback: IsValidCallback) {
    CoroutineScope(Dispatchers.IO).launch {
        delay(350)

        val registerAuthService =
            RegisterAuthService(
                mAuth,
                databaseRef
            )
        registerAuthService.registerWithEmail(email, password, username, callback)
    }
}
