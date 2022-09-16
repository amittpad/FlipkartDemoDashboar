package com.app.birlasoft.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.databinding.DataBindingUtil;

import com.app.birlasoft.R;
import com.app.birlasoft.base.BaseActivity;
import com.app.birlasoft.databinding.ActivitySplashBinding;
import com.app.birlasoft.ui.home.view.HomeActivity;


public class SplashActivity extends BaseActivity {

    private String mUserId, mAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashBinding binding = DataBindingUtil.setContentView(SplashActivity.this, R.layout.activity_splash);

        //Animation
        Animation bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up_animation);
        binding.image.setAnimation(bottomAnimation);

        displaySplashScreen();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    private void displaySplashScreen() {
        int SPLASH_TIME_OUT = 3000;
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent;
            intent = new Intent(SplashActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }, SPLASH_TIME_OUT);
    }
}