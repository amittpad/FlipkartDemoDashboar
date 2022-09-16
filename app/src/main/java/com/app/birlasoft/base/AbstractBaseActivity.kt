package com.app.birlasoft.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.ActivityInfo

abstract class AbstractBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}