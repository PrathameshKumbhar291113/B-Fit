package com.prathameshkumbhar.bfit.coremodule

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.prathameshkumbhar.bfit.databinding.ActivitySplashBinding
import com.prathameshkumbhar.bfit.loginmodule.SignInAndSignUpActivity
import com.prathameshkumbhar.bfit.mainmodule.HomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.activities.start

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharePrefSplash : SharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
        var isLoggedIn = sharePrefSplash.getBoolean("flag",false)

        if (isLoggedIn){
            lifecycleScope.launch{
                delay(2000)
                start<HomeActivity>(){
                    finish()
                }
            }
        }else{
           lifecycleScope.launch {
               delay(2000)
                start<SignInAndSignUpActivity>(){
                    finish()
                }
            }
        }
    }
}