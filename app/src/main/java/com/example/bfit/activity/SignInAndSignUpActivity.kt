package com.example.bfit.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bfit.R
import com.example.bfit.databinding.ActivitySignInAndSignUpBinding
import com.example.bfit.fragment.SignUpFragment

class SignInAndSignUpActivity : AppCompatActivity() {
    lateinit var binding : ActivitySignInAndSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding = ActivitySignInAndSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
         replaceFragment(SignUpFragment())
    }
    //logic - if successfully signIn then go to the OnBoard Activity else do sign In again
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.signIn_SignUp_Frag_Container,fragment)
        fragmentTransaction.commit()
    }
}