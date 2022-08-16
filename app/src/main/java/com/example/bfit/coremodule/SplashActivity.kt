package com.example.bfit.coremodule

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.bfit.R
import com.example.bfit.loginmodule.SignInAndSignUpActivity
import com.example.bfit.onboardingmodule.OnboardActivity
import splitties.activities.start

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            start<SignInAndSignUpActivity>(){
                finish()
            }
        },2000)

    }

}