package com.prathameshkumbhar.bfit.homemodule.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.prathameshkumbhar.bfit.coremodule.BaseActivity
import com.prathameshkumbhar.bfit.databinding.ActivityStepsCounterBinding
import com.prathameshkumbhar.bfit.homemodule.service.StepCounterService
import com.prathameshkumbhar.bfit.homemodule.service.StepCounterService.Companion.STEPS
import com.prathameshkumbhar.bfit.homemodule.service.StepCounterService.Companion.STEP_COUNT_UPDATE
import com.prathameshkumbhar.bfit.homemodule.service.StepsReceiver

class StepsCounterActivity : BaseActivity() {
    lateinit var binding: ActivityStepsCounterBinding
    private val stepService = StepCounterService()
    private var isRunning = false
    private lateinit var sharedPref: SharedPreferences

    companion object {
        const val IS_RUNNING = "isRunning"
    }

    // receives steps broadcast from StepCounter service
    private val stepsReceiver: StepsReceiver = object : StepsReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            isRunning =  intent.getBooleanExtra(IS_RUNNING, true)
            val steps = intent.getIntExtra(STEPS, 0).toString()
            val stepsFromPref = sharedPref.getFloat(stepService.todayDate, 0f)
            binding.stepsCounter.text = stepsFromPref.toInt().toString()
            // steps progress bar set up
//            progressBar.apply {
//                setProgressWithAnimation(steps.toFloat(), 2000)
//                startAngle = 180f
//            }
            Log.d("stepsUI", steps)

//           ` val distance = intent.getDoubleExtra(DISTANCE_METER, 0.0).toString()
//            tvDistance.text = getString(R.string.distance_km, distance)
//
//            val acc = sharedPref.getString(stepService.dateForAcc, "0")
//            tvAcceleration.text = getString(R.string.walking_acc, acc)
//
//            val calories = sharedPref.getFloat(stepService.dateForCalories, 0f)
//            tvCalories.text = getString(R.string.calories_cal, calories.toString())
//
//            // persists the day's count to db and stops counter service
//            if (currentUser !== null) {
//                val owner = currentUser!!.email
//                val stepsToDB = StepCounts (stepService.todayDate, owner,
//                    stepsFromPref, calories)
//
//                // persists step counts and calories to room DB
//                appViewModel.addStepsToDb(stepsToDB)
//            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStepsCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // request permission to use step counter
        if (Build.VERSION.SDK_INT >= 29) {
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
                requestPermissions(arrayOf(Manifest.permission.ACTIVITY_RECOGNITION), 2)
            }
        }

//        binding.stepsCounter.text = sharedPref.getFloat(stepService.todayDate, 0f).toString()
        binding.steps2.text = intent.getIntExtra(STEPS, 0).toString()

        binding.startWalkingBtn.setOnClickListener {
            startService(this@StepsCounterActivity)
        }
        binding.stopWalkingBtn.setOnClickListener {
            stopService(this@StepsCounterActivity)
        }

//        //Fetches the current log user
//        fetchCurrentUser()
//
//        //Checks if the user is log in or not
//        verifyuserIsLogrdIn()
//
//
//        // set bottom navigation bar
//        bottom_navigation.apply {
//            selectedItemId = R.id.home
//            setOnNavigationItemSelectedListener(
//                BottomNavListener(this@MainActivity, MainActivity::class.java )
//            )
//        }
//        handleActionBarClicks() // action bar navigation handler
//        topAppBar?.apply {
//            setLogo(R.drawable.icon_logo)
//
//        }
//
//        // loads charts on home screen
//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.fragments_container_home, StepsBarChartFragment.newInstance(),
//                getString(R.string.tag_barchart))
//            .commit()
//
//        tvCalories.setOnClickListener(OnclickListener())
//
//
//        appViewModel = ViewModelProvider(this).get(WorkoutCompanionViewModel::class.java)
//        sharedPref = getSharedPreferences(PREF, Context.MODE_PRIVATE)
//
//        tvSteps.apply {
//            if (text == getText(R.string.number_of_steps))
//            // verify and loads step counts from preferences
//                text = sharedPref.getFloat(stepService.todayDate, 0f).toInt().toString()
//            setOnClickListener(OnclickListener())
//        }
//        // loads values from shared preference when steps service is not started
//        updateCountInfo(tvDistance, stepService.dateForDistance, R.string.distance_km)
//        updateCountInfo(tvCalories, stepService.dateForCalories, R.string.calories_cal)
//        val acc = sharedPref.getString(stepService.dateForAcc, "0")
//        tvAcceleration.text = getString(R.string.walking_acc, acc)
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(stepsReceiver, IntentFilter(STEP_COUNT_UPDATE))
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(stepsReceiver)
    }
    // foreground or background service started based on the SDK version
    private fun startService (context: Context) {

        if (!isRunning) {
            val startIntent = Intent(context, StepCounterService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)  {
                context.startForegroundService(startIntent)
            }else context.startService(startIntent)

            successToast("Counter Started")
        }else successToast("Counter Already Started")
    }

    private fun stopService(context: Context) {
        if (isRunning){
            context.stopService(Intent(context, StepCounterService::class.java))
            successToast( "Counter Stoped")
            isRunning = false
        }
    }


//    private fun updateCountInfo(tv: TextView, txt:String, string: Int){
//        if (tv.text == getText(string))
//            tv.text = getString(string, sharedPref.getFloat(txt, 0.0f).toString())
//    }`

}