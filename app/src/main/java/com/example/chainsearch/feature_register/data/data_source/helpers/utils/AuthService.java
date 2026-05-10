package com.example.chainsearch.feature_register.data.data_source.helpers.utils;

import com.example.chainsearch.feature_register.data.data_source.helpers.callbacks.IsValidCallback;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

import static com.example.chainsearch.feature_register.data.data_source.helpers.utils.RegisterAuthServiceHelperKt.addNewUser;

public class AuthService {
    public static void registerGoogleEmail(AuthData authData, String result, IsValidCallback callback) {
        AuthCredential credential = GoogleAuthProvider.getCredential(result, null);
        authData.getMAuth().signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    decideWhetherYouAddUserOrNotGoogle(task, authData, new IsValidCallback() {
                        @Override
                        public void onRes(boolean isValid, String uid) {
                            callback.onRes(isValid, uid);
                        }
                    });
                });
    }

    private static void decideWhetherYouAddUserOrNotGoogle(Task<?> task, AuthData authData, IsValidCallback callback) {
        if (task.isSuccessful()) {
            addNewUser(authData, callback);
        } else {
            callback.onRes(false, null);
        }
    }
}