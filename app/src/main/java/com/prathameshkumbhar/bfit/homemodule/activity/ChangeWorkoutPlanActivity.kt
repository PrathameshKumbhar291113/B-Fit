package com.prathameshkumbhar.bfit.homemodule.activity

import android.os.Bundle
import com.prathameshkumbhar.bfit.coremodule.BaseActivity
import com.prathameshkumbhar.bfit.databinding.ActivityChangeWorkoutPlanBinding

class ChangeWorkoutPlanActivity : BaseActivity() {
    lateinit var binding: ActivityChangeWorkoutPlanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeWorkoutPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}