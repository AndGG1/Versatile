package com.example.chainsearch.feature_register.data.data_source.helpers.utils

import android.os.Build
import com.example.chainsearch.feature_register.data.data_source.helpers.callbacks.IsValidCallback
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import java.util.Calendar

fun isCallbackNull(callback : Any?) : Boolean {
    return callback == null
}

fun isBuildVersionValid() : Boolean {
    return Build.VERSION.SDK_INT < Build.VERSION_CODES.O
}

fun addNewUser(authData: AuthData, finalCallback: IsValidCallback) {
    val user: FirebaseUser? = authData.mAuth.currentUser
    if (user != null) {
        val uid = user.uid
        authData.uid = uid
        addUser(authData, finalCallback, null)
    } else {
        finalCallback.onRes(false, null)
    }
}

fun addUser(authData: AuthData, callback: IsValidCallback, user: User?) {
    val newUser = User(
        authData.username,
        Calendar.getInstance().time.toString(),
        0.0,
        0
    )

    authData.databaseRef.child("users")
        .child(authData.uid!!).setValue(user ?: newUser)
        .addOnCompleteListener(OnCompleteListener<Void?> { task: Task<Void?> ->
            callback.onRes(task.isSuccessful, authData.uid)
        })
}
