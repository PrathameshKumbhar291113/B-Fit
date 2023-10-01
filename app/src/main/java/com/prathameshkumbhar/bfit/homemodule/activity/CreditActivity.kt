package com.prathameshkumbhar.bfit.homemodule.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prathameshkumbhar.bfit.databinding.ActivityCreditBinding

class CreditActivity : AppCompatActivity() {
    lateinit var binding : ActivityCreditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.creditsToolBar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}