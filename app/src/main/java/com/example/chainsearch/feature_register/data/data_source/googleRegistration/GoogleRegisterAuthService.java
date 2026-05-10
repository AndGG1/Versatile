package com.example.chainsearch.feature_register.data.data_source.googleRegistration;

import android.content.Context;
import android.os.Build;

import androidx.annotation.Nullable;

import com.example.chainsearch.feature_register.data.data_source.helpers.callbacks.CredentialCallback;
import com.example.chainsearch.feature_register.data.data_source.helpers.callbacks.IsValidCallback;
import com.example.chainsearch.feature_register.data.data_source.helpers.utils.AuthData;
import com.example.chainsearch.feature_register.data.data_source.helpers.utils.AuthService;
import com.example.chainsearch.feature_register.data.data_source.helpers.utils.CredentialManagerHelperKt;
import com.example.chainsearch.feature_register.data.data_source.helpers.utils.HandleCredentialHelperKt;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class GoogleRegisterAuthService {
    private final FirebaseAuth mAuth;
    private final DatabaseReference databaseRef;

    public GoogleRegisterAuthService(FirebaseAuth mAuth, DatabaseReference databaseRef) {
        this.mAuth = mAuth;
        this.databaseRef = databaseRef;
    }

    public void registerUserWithGoogle(Context context, String username, IsValidCallback callback) {
        boolean isValidVersion = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
        if (!isValidVersion) return;

        HandleCredentialHelperKt.handleCredential(context,
                CredentialManagerHelperKt.getCredentialRequest(),
                new CredentialCallback() {
                    @Override
                    public void onRes(boolean worked, @Nullable String result) {
                        if (worked) {
                            AuthData authData = new AuthData(username, null, databaseRef, mAuth);
                            AuthService.registerGoogleEmail(authData, result, callback);
                        }
                    }
                }
        );
    }
}
