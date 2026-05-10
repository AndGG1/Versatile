package com.example.chainsearch.feature_register.data.data_source.basicRegistration;
import static com.example.chainsearch.feature_register.data.data_source.helpers.utils.RegisterAuthServiceHelperKt.addNewUser;
import static com.example.chainsearch.feature_register.data.data_source.helpers.utils.RegisterAuthServiceHelperKt.isBuildVersionValid;
import static com.example.chainsearch.feature_register.data.data_source.helpers.utils.RegisterAuthServiceHelperKt.isCallbackNull;

import com.example.chainsearch.feature_register.data.data_source.helpers.callbacks.IsValidCallback;
import com.example.chainsearch.feature_register.data.data_source.helpers.utils.AuthData;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class RegisterAuthService {
    private final FirebaseAuth mAuth;
    private final DatabaseReference databaseRef;

    public RegisterAuthService(FirebaseAuth mAuth, DatabaseReference databaseRef) {
        this.mAuth = mAuth;
        this.databaseRef = databaseRef;
    }

    public void registerWithEmail(String email, String password, String username, IsValidCallback callback) {
        if (isCallbackNull(callback)) {
            callback = new IsValidCallback() {
                @Override
                public void onRes(boolean isValid, String uid) {

                }
            };
        }

        if (isBuildVersionValid()) {
            callback.onRes(false, null);
            return;
        }

        IsValidCallback finalCallback = callback;
        AuthData authData = new AuthData(username, null, databaseRef, mAuth);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    decideWhetherYouAddUserOrNot(task, authData, finalCallback);
                });
    }

    private void decideWhetherYouAddUserOrNot(Task<?> task, AuthData authData, IsValidCallback finalCallback) {
        if (task.isSuccessful()) {
            addNewUser(authData, finalCallback);
        } else {
            finalCallback.onRes(false, null);
        }
    }
}
