package com.superposition.ingredienttalk.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.github.ybq.android.spinkit.SpinKitView;
import com.superposition.ingredienttalk.R;
import com.superposition.ingredienttalk.util.Constants;
import com.superposition.ingredienttalk.util.ToastHelper;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    final int REQUEST_PERMISSION = 101;

    TextView txtProgress;
    SpinKitView spinKitView;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txtProgress = findViewById(R.id.txtSplashLoading);
        spinKitView = findViewById(R.id.spinKitSplash);
        sharedPreferences = getSharedPreferences(Constants.MY_PREF, MODE_PRIVATE);

        txtProgress.setVisibility(View.GONE);
        spinKitView.setVisibility(View.GONE);

        checkPermissions();
    }

    private void checkPermissions() {
        List<String> permissionNeeded = new ArrayList<>();
        for (String perm : permissions) {
            int result = ActivityCompat.checkSelfPermission(SplashActivity.this, perm);
            if (result != PackageManager.PERMISSION_GRANTED) {
                permissionNeeded.add(perm);
            }
        }
        if (permissionNeeded.isEmpty()) {
            getCall();
        } else {
            ActivityCompat.requestPermissions(SplashActivity.this, permissionNeeded.toArray(new String[permissionNeeded.size()]), REQUEST_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_DENIED) {
                    allGranted = false;
                    break;
                }
            }
            if (allGranted) {
                getCall();
            } else {
                new ToastHelper().makeToast(SplashActivity.this, "All permissions needed!", Toast.LENGTH_LONG);
                finishAffinity();
            }
        }
    }

    private void getCall() {
        txtProgress.setVisibility(View.VISIBLE);
        spinKitView.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPreferences.getBoolean(Constants.IS_LOGGED_IN, false)) {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2500);

    }
}