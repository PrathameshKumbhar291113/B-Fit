package com.prathameshkumbhar.bfit.coremodule

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.prathameshkumbhar.bfit.databinding.ActivitySplashBinding
import com.prathameshkumbhar.bfit.loginmodule.SignInAndSignUpActivity
import com.prathameshkumbhar.bfit.mainmodule.HomeActivity
import splitties.activities.start

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null){
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                start<HomeActivity>(){
                    finish()
                }
            },1000)
        }else{
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                start<SignInAndSignUpActivity>(){
                    finish()
                }
            },2000)
        }
    }
}