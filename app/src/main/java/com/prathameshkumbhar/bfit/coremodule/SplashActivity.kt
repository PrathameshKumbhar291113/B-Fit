package com.prathameshkumbhar.bfit.coremodule

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.prathameshkumbhar.bfit.BuildConfig
import com.prathameshkumbhar.bfit.databinding.ActivitySplashBinding
import com.prathameshkumbhar.bfit.loginmodule.activity.SignInAndSignUpActivity
import com.prathameshkumbhar.bfit.mainmodule.activity.HomeActivity
import com.prathameshkumbhar.bfit.onboardingmodule.activity.OnboardActivity
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

        binding.versionNumTv.text = BuildConfig.VERSION_NAME

        val sharePrefSplash : SharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
        val sharePrefOnboarded : SharedPreferences = getSharedPreferences("onBoardCheck", Context.MODE_PRIVATE)

        var isLoggedIn = sharePrefSplash.getBoolean("flag",false)
        var checkOnboardComplete = sharePrefOnboarded.getBoolean("isOnboardComplete",false)


        if (isLoggedIn && checkOnboardComplete) {
            lifecycleScope.launch{
                delay(2000)
                start<HomeActivity>(){
                    finish()
                }
            }
        }else if(!isLoggedIn){
            lifecycleScope.launch {
                delay(2000)
                start<SignInAndSignUpActivity>(){
                    finish()
                }
            }
        }else if (!checkOnboardComplete){
            lifecycleScope.launch {
                delay(2000)
                start<OnboardActivity>(){
                    finish()
                }
            }
        }

    }
}