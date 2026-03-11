package com.example.chainsearch.initialAction.loadingPack;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//TODO: Add Firebase external check + others(optional)
record ExternalListener(
        boolean hasInternetConnection, List<String> perms, boolean hasStorage) {
    //empty
}

public class LoadingChecks {
    protected static ExternalListener checkExteriorEnv(Context context, int minimumFreeGB) {
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
        File storageFile = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(storageFile.getPath());
        long freeBytes = stat.getAvailableBytes();
        if (freeBytes >= (minimumFreeGB * 1_000_000L - 100_000)) {
            hasEnoughSpace = true;
        }

        ExternalListener ex = new ExternalListener(hasInternet, unauthorizedPerms, hasEnoughSpace);
        Log.d("test+ver", ex.toString());
        return ex;
    }
}
