package com.example.myapp.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapp.MainActivity;
import com.example.myapp.R;
import com.example.myapp.signuporsignin.SignUpOrSignInActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .getBoolean("isFirstRun", true);

                if (isFirstRun) {
                    //show start activity
                    startActivity(new Intent(SplashActivity.this, SignUpOrSignInActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }

                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                        .putBoolean("isFirstRun", false).commit();

                finish();
            }
        }, 3000);
    }
}
