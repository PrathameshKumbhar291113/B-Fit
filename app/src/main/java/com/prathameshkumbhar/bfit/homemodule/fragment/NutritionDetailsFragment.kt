package com.prathameshkumbhar.bfit.homemodule.fragment

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.prathameshkumbhar.bfit.coremodule.BaseFragment
import com.prathameshkumbhar.bfit.databinding.FragmentNutritionDetailsBinding
import com.prathameshkumbhar.bfit.homemodule.service.StepCounterService
import com.prathameshkumbhar.bfit.homemodule.service.StepCounterService.Companion.STEPS
import com.prathameshkumbhar.bfit.homemodule.service.StepsReceiver


class StepCounterFragment : BaseFragment() {
    private val stepService = StepCounterService()
    private var isRunning = false
    private var _binding : FragmentNutritionDetailsBinding? = null
    private val binding get() = _binding!!
    private val navController by lazy {
        findNavController()
    }
    companion object {
        const val IS_RUNNING = "isRunning"
    }

    // receives steps broadcast from StepCounter service
    private val stepsReceiver: StepsReceiver = object : StepsReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            isRunning =  intent.getBooleanExtra(IS_RUNNING, true)
            val steps = intent.getIntExtra(STEPS, 0).toString()
//            val stepsFromPref = sharedPref.getFloat(stepService.todayDate, 0f)
            binding.stepsCount.text = steps.toInt().toString()
            // steps progress bar set up
//            progressBar.apply {
//                setProgressWithAnimation(steps.toFloat(), 2000)
//                startAngle = 180f
//            }
//            Log.d("stepsUI", steps)
//
//            val distance = intent.getDoubleExtra(DISTANCE_METER, 0.0).toString()
//            tvDistance.text = getString(R.string.distance_km, distance)
//
//            val acc = sharedPref.getString(stepService.dateForAcc, "0")
//            tvAcceleration.text = getString(R.string.walking_acc, acc)
//
//            val calories = sharedPref.getFloat(stepService.dateForCalories, 0f)
//            tvCalories.text = getString(R.string.calories_cal, calories.toString())
//
//

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNutritionDetailsBinding.inflate(inflater, container, false)

        // request permission to use step counter
        if (Build.VERSION.SDK_INT >= 29) {
            if(ContextCompat.checkSelfPermission(context!!,
                    Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
                requestPermissions(arrayOf(Manifest.permission.ACTIVITY_RECOGNITION), 2)
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
//        registerReceiver(stepsReceiver, IntentFilter(STEP_COUNT_UPDATE))
    }

    override fun onStop() {
        super.onStop()
        (stepsReceiver)
    }
    // foreground or background service started based on the SDK version
    private fun startService (context: Context) {

        if (!isRunning) {
            val startIntent = Intent(context, StepCounterService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)  {
                context.startForegroundService(startIntent)
            }else context.startService(startIntent)

            successToast("COUNTER STARTED")
        }else successToast("COUNTING..")
    }

    private fun stopService(context: Context) {
        if (isRunning){
            context.stopService(Intent(context, StepCounterService::class.java))
            errorToast("COUNTER STOPPED")
            isRunning = false
        }
    }

}