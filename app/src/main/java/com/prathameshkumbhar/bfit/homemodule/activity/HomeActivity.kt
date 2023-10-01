package com.prathameshkumbhar.bfit.homemodule.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.prathameshkumbhar.bfit.R
import com.prathameshkumbhar.bfit.databinding.ActivityHomeBinding
import splitties.activities.start


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.homeActivityToolBar)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.homeActivity_fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView,navController)
        setupActionBarWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.showExerciseFragment -> {
                    binding.homeActivityToolBar.navigationIcon = null
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }

                R.id.dietPlanFragment -> {
                    binding.homeActivityToolBar.navigationIcon = null
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }

//                R.id.stepCounterFragment -> {
//                    binding.homeActivityToolBar.navigationIcon = null
//                    binding.bottomNavigationView.visibility = View.VISIBLE
//                }

                R.id.profileFragment -> {
                    binding.homeActivityToolBar.navigationIcon = null
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }

        binding.stepsImage.setOnClickListener{
            start<StepsCounterActivity>()
        }
    }
}