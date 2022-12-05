package com.prathameshkumbhar.bfit.mainmodule.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prathameshkumbhar.bfit.R
import com.prathameshkumbhar.bfit.databinding.ActivityChangeDietPlanBinding

class ChangeDietPlanActivity : AppCompatActivity() {
     lateinit var binding: ActivityChangeDietPlanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_diet_plan)
    }
}