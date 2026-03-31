package com.example.chainsearch.initialAction.auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

interface IsValidCallback {
    void onRes(boolean isValid, String uid);
}

public class RegisterUserFunctionality {
    private static final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static final DatabaseReference databaseRef =
            FirebaseDatabase.getInstance().getReference("users");
    public static void registerUser(String email, String username, String password, IsValidCallback callback) {

    }
}
