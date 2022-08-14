package com.example.bfit.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bfit.R
import com.example.bfit.databinding.ActivityOnboardBinding
import com.example.bfit.fragment.GenderFragment

class OnboardActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(GenderFragment())

    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.onBoardAct_fragContainer,fragment)
        fragmentTransaction.commit()
    }
}