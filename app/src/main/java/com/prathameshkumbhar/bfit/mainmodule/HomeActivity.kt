package com.prathameshkumbhar.bfit.mainmodule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation.Model
import com.prathameshkumbhar.bfit.R
import com.prathameshkumbhar.bfit.databinding.ActivityHomeBinding
import com.prathameshkumbhar.bfit.mainmodule.fragment.ExerciseShowCaseFragment
import com.prathameshkumbhar.bfit.mainmodule.fragment.NutritionDetailsFragment


class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var toSetFragment = binding.bottomNavigationView

        toSetFragment.add(Model(1,R.drawable.ic_home))
        toSetFragment.add(Model(2,R.drawable.ic_food_bank))
        toSetFragment.add(Model(3,R.drawable.ic_account))

        toSetFragment.show(1,true)
        replaceFragment(ExerciseShowCaseFragment())


        binding.bottomNavigationView.setOnClickMenuListener { model: Model? ->

            when(model!!.id){
                1 -> replaceFragment(ExerciseShowCaseFragment())
                2 -> replaceFragment(NutritionDetailsFragment())
                3 -> replaceFragment(NutritionDetailsFragment())
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.homeActivity_fragmentContainer,fragment)
        fragmentTransaction.commit()
    }
}