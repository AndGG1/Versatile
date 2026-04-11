package com.example.chainsearch.initialAction.auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Objects;

import android.os.Build;

import androidx.annotation.NonNull;

interface IsValidCallback {
    void onRes(boolean isValid, String uid);
}

public class RegisterUserFunctionality {
    private static final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static final DatabaseReference databaseRef =
            FirebaseDatabase.getInstance("https://versatile-bf2d4-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
    public static void registerEmail(String email, String password, String username, IsValidCallback callback) {
        callback = callback == null ? new IsValidCallback() {
            @Override
            public void onRes(boolean isValid, String uid) {

            }
        } : callback;

        email = email.trim();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            callback.onRes(false, null);
            return;
        }

        IsValidCallback finalCallback = callback;
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            String uid = user.getUid();
                            addUser(username, uid, finalCallback, null);
                        } else {
                            finalCallback.onRes(false, null);
                        }
                    } else {
                        finalCallback.onRes(false, null);
                    }
                });
    }

    public static void updateUser(String uid, String username, IsValidCallback callback, Object... objArr) {
        callback = callback == null ? new IsValidCallback() {
            @Override
            public void onRes(boolean isValid, String uid) {

            }
        } : callback;

        IsValidCallback finalCallback = callback;
        databaseRef.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String newDate = null;
                double newAvg = -1.0;
                int newClicks = -1;

                for (Object data : objArr) {
                    if (data instanceof String) {
                        newDate = (String) data;
                    } else if (data instanceof Double) {
                        newAvg = (double) data;
                    } else if (data instanceof Integer) {
                        newClicks = (int) data;
                    }
                }

                var iterator = snapshot.getChildren().iterator();
                boolean flag = false;
                while (iterator.hasNext()) {
                    var val = iterator.next().getValue();
                    if (val instanceof String && flag) {
                        newDate = newDate == null ? (String) val : newDate;
                    } else if (val instanceof Double) {
                        newAvg = newAvg == -1.0 ? (double) val : newAvg;
                    } else if (val instanceof Integer) {
                        newClicks = newClicks == -1 ? (int) val : newClicks;
                    }
                    flag = true;
                }

                User updatedUser = new User(
                        username,
                        newDate,
                        newAvg,
                        newClicks
                );

                addUser(username, uid, finalCallback, updatedUser);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                finalCallback.onRes(false, uid);
            }
        });
    }

    //Currently not needed
    //Used in tests
    public static void removeUser(IsValidCallback callback, String uid) {
        Objects.requireNonNull(mAuth.getCurrentUser()).delete()
                .addOnCompleteListener(task -> {
                    callback.onRes(task.isSuccessful(), uid);
                });
    }

    private static void addUser(String username, String uid, IsValidCallback callback, User user) {
        User newUser = new User(
                username,
                Calendar.getInstance().getTime().toString(),
                0.0,
                0
        );

        databaseRef.child("users")
                .child(uid).setValue(user == null ? newUser : user)
                .addOnCompleteListener(task -> {
                    callback.onRes(task.isSuccessful(), uid);
                });
        }
}
