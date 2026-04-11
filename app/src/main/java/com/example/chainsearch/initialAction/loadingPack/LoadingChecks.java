package com.example.chainsearch.initialAction.loadingPack;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.StatFs;

import androidx.core.content.ContextCompat;

import com.example.chainsearch.initialAction.auth.SecurityAlgosKt;
import com.example.chainsearch.initialAction.viewModels.states.ExternalListener;
import com.example.chainsearch.initialAction.viewModels.states.InternalListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LoadingChecks {
    //TODO: Add Firebase external check + API external check!!!
    public static ExternalListener checkExteriorEnv(Context context, double percentage) {
        //Internet & Network State check
        boolean hasInternet = true;
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isAvailable() || !networkInfo.isConnectedOrConnecting()) {
            hasInternet = false;
        }

        //Permissions check
        String[] perms = new String[]{Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE};
        List<String> unauthorizedPerms = new ArrayList<>();

        for (String p : perms) {
            if (ContextCompat.checkSelfPermission(context, p) != PackageManager.PERMISSION_GRANTED) {
                unauthorizedPerms.add(p);
            }
        }

        //Available Storage check
        boolean hasEnoughSpace = false;
        File storageFile = context.getFilesDir();
        StatFs stat = new StatFs(storageFile.getPath());

        long freeBytes = stat.getAvailableBytes();
        long totalBytes = stat.getTotalBytes();

        if (freeBytes >= (double) totalBytes / 100 * percentage) {
            hasEnoughSpace = true;
        }

        return new ExternalListener(
                hasInternet, unauthorizedPerms, hasEnoughSpace);
    }

    //TODO: Add Database internal check!!!
    public static InternalListener checkInternalEnv() {
        //delayAction();

        //TODO: Complete this when Database Impl. is ready!!!
        return new InternalListener(true);
    }

    private static void delayAction() {
        String saltKey = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            saltKey = SecurityAlgosKt.generateSaltKey(32);
        }
        for (int i = 1; i <= 10; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                SecurityAlgosKt.convertTo_Argon2id("12345678", saltKey);
            }
        }
    }
}