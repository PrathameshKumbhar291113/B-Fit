package com.prathameshkumbhar.bfit.mainmodule

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation.Model
import com.prathameshkumbhar.bfit.R
import com.prathameshkumbhar.bfit.databinding.ActivityHomeBinding
import com.prathameshkumbhar.bfit.mainmodule.fragment.FemaleExerciseFragment
import com.prathameshkumbhar.bfit.mainmodule.fragment.MaleExerciseFragment
import com.prathameshkumbhar.bfit.mainmodule.fragment.NutritionDetailsFragment
import com.prathameshkumbhar.bfit.mainmodule.fragment.ProfileFragment


class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var toSetFragment = binding.bottomNavigationView

        toSetFragment.add(Model(1,R.drawable.ic_home))
        toSetFragment.add(Model(2,R.drawable.ic_diet))
        toSetFragment.add(Model(3,R.drawable.ic_person_profile))



        val sharePrefGender: SharedPreferences = getSharedPreferences("genderCheck", Context.MODE_PRIVATE)
        var checkGenderMale = sharePrefGender.getBoolean("isMaleChecked", true)
        if (checkGenderMale){
            toSetFragment.show(1,true)
            replaceFragment(MaleExerciseFragment())
        }else{
            toSetFragment.show(1,true)
            replaceFragment(FemaleExerciseFragment())
        }

        binding.bottomNavigationView.setOnClickMenuListener { model: Model? ->

            when(model!!.id){
                1 -> {
                    if (checkGenderMale){
                        toSetFragment.show(1,true)
                        binding.homeActivityToolBar.title = "Home"
                        replaceFragment(MaleExerciseFragment())
                    }else{
                        toSetFragment.show(1,true)
                        binding.homeActivityToolBar.title = "Home"
                        replaceFragment(FemaleExerciseFragment())
                    }
                }
                2 -> {
                    replaceFragment(NutritionDetailsFragment())
                    binding.homeActivityToolBar.title = "Nutrition"
                }
                3 -> {
                    replaceFragment(ProfileFragment())
                    binding.homeActivityToolBar.title = "Profile"
                }
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