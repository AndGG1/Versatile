package com.example.chainsearch.feature_register.data.data_source.helpers.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

data class AuthData(
    val username: String,
    var uid: String?,
    val databaseRef: DatabaseReference,
    val mAuth: FirebaseAuth
)
