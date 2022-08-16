package com.example.bfit.loginmodule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bfit.databinding.ActivitySignInAndSignUpBinding

class SignInAndSignUpActivity : AppCompatActivity() {
    lateinit var binding : ActivitySignInAndSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding = ActivitySignInAndSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}