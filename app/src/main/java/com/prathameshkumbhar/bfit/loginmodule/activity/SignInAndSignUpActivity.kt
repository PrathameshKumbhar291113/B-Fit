package com.prathameshkumbhar.bfit.loginmodule.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prathameshkumbhar.bfit.databinding.ActivitySignInAndSignUpBinding

class SignInAndSignUpActivity : AppCompatActivity() {
    lateinit var binding : ActivitySignInAndSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding = ActivitySignInAndSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}