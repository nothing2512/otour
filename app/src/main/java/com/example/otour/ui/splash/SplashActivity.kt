package com.example.otour.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.otour.R
import com.example.otour.databinding.ActivitySplashBinding
import com.example.otour.ui.auth.activity.LoginActivity
import com.example.otour.ui.dashboard.DashboardActivity
import com.example.otour.utils.MainPreference
import com.example.otour.utils.getBinding
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private val preference: MainPreference by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBinding<ActivitySplashBinding>(R.layout.activity_splash)

        Thread.sleep(1000)

        val clazz = if (preference.isLogin) DashboardActivity::class.java
        else LoginActivity::class.java

        startActivity(Intent(applicationContext, clazz))
    }
}
