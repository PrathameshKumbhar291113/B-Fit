package com.prathameshkumbhar.bfit.homemodule.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.prathameshkumbhar.bfit.R
import com.prathameshkumbhar.bfit.homemodule.activity.HomeActivity
import com.prathameshkumbhar.bfit.homemodule.fragment.StepCounterFragment
import java.text.DateFormat
import java.util.Date
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

class StepCounterService : Service(), SensorEventListener {
    val todayDate: String = DateFormat.getDateInstance(DateFormat.MEDIUM).format(Date())
    val dateForDistance = "$todayDate-km"
    val dateForCalories = "$todayDate-kcal"
    val dateForAcc = "$todayDate-acc"

    private var stepsBeforeReset = 0f
    private var previousSteps = 0f
    private var currentSteps = 0
    private var rawSteps: Float = 0f
    private var calories = 0.0
    private var magnitudeAcc = 0.0f
    private var isNotCalibrated = true
    private var calibrationArray = ArrayList<FloatArray>()
    private var offsetValues : Array<Float> = arrayOf(0f, 0f, 0f)
    private var sensorManager: SensorManager? = null
    private var sCounter: Sensor? = null
    private var accelerometer: Sensor? = null
    private var linearAccelerometer: Sensor? = null
    private lateinit var sharedPref: SharedPreferences
    private lateinit var mHandler: Handler

    override fun onCreate() {
        super.onCreate()
        // initialization of system services
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sCounter = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        accelerometer = sensorManager?.getDefaultSensor((Sensor.TYPE_ACCELEROMETER))
        linearAccelerometer = sensorManager?.getDefaultSensor((Sensor.TYPE_LINEAR_ACCELERATION))

        mHandler = Handler(Looper.getMainLooper())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        sharedPref = getSharedPreferences(PREF, Context.MODE_PRIVATE)

        // registration of sensor types
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER))  {
            sensorManager?.registerListener(this, sCounter, SensorManager.SENSOR_DELAY_NORMAL)
            sensorManager?.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
            sensorManager?.registerListener(this, linearAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
            sCounter?.name?.let { Log.d("PERMITTED", it) }

        } else  toast("")
        // starts foreground service when onStartCommand is called
        startForeground(SERVICE_ID, createNotification())

        Log.d("offset", rawSteps.toString())

        return START_STICKY
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor == sCounter) {
            // val todaySteps = sharedPref.getFloat(todayDate, 0f)
            updateAndResetCount(event.values[0])
            rawSteps = event.values[0]
            currentSteps =( rawSteps.toInt() + stepsBeforeReset.toInt()).minus(previousSteps.toInt())
            saveSteps()
            Log.d("$todayDate rawSteps", rawSteps.toString())

            calculateCalories()

            sendBroadcast(Intent().apply {
                action = STEP_COUNT_UPDATE
                putExtra(STEPS, currentSteps)
                putExtra(DISTANCE_METER, calculateDistanceFromSteps())
                putExtra(StepCounterFragment.IS_RUNNING, true)
            })
        }
        // records walking linear walking acceleration
        if (event.sensor == linearAccelerometer ) {
            if (isNotCalibrated){
                calibrateLinearAcc(event.values)
            }else walkingAcceleration(event.values)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.d("${sensor?.name}", "$accuracy")
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        // unregister sensors releasing resources
        try {
            sCounter.also { sensorManager?.unregisterListener(this ) }
            accelerometer.also { sensorManager?.unregisterListener(this) }
            linearAccelerometer.also { sensorManager?.unregisterListener(this) }
        }catch (e:Exception) {
            e.message?.let { Log.d("StepCounter", it) }
        }
    }

    private fun saveSteps() {
        // step counts are save to shared pref for future updates
        val previousRawSteps =rawSteps
        sharedPref.edit().putFloat(todayDate, currentSteps.toFloat()).apply()
        sharedPref.edit().putFloat(PREVIOUS_STEPS, previousRawSteps).apply()
        sharedPref.edit().putString(dateForDistance, calculateDistanceFromSteps().toString()).apply()
    }

    private fun calculateDistanceFromSteps(): Double{
        val aveStepLength = 0.6858
        val km = ((aveStepLength * currentSteps) / 1000 * 10.0).roundToInt() /10.0 // 2 decimal place
        sharedPref.edit().putFloat(dateForDistance, km.toFloat()).apply()
        return km
    }

    private fun updateAndResetCount (rawValue: Float) {
        val todaySteps = sharedPref.getFloat(todayDate, 0f)
        if (rawValue == 0f && todaySteps > 0f) {
            stepsBeforeReset += todaySteps.toInt()
        }else if (rawValue > 0f && todaySteps == 0f){
            previousSteps = sharedPref.getFloat(PREVIOUS_STEPS, 0f)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID, "B FIT",
                NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "MY STEPS CHANNEL"
            }
            ( getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).apply {
                createNotificationChannel(channel)
            }
        }
    }

    private fun createNotification() : Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID).apply {
            val intent = Intent(this@StepCounterService, HomeActivity::class.java)
                .apply {  Intent.FLAG_ACTIVITY_CLEAR_TASK  }
            setSmallIcon(R.drawable.ic_notification)
            setContentText("Steps Notification")
            setContentText(getString(R.string.steps, currentSteps))
            priority = NotificationCompat.PRIORITY_LOW
            setContentIntent(
                PendingIntent.getActivity(
                    this@StepCounterService, 0, intent, PendingIntent.FLAG_IMMUTABLE
                )
            )
            setAutoCancel(true)
        }.build()
    }

    private fun calculateCalories () {
        // calculates kcal burned after every 1 mins of moderate walking , MET = 3.5
        mHandler.postDelayed(
            {
                val fromPref = sharedPref.getString(WEIGHT, "0")
                val usersWeight = if (fromPref !== "") fromPref?.toInt() else 0
                if (usersWeight !== null) {
                    calories += ((((MET * usersWeight)/60)  ) * 10.0 ).roundToInt() / 10.0
                    sharedPref.edit().putFloat(dateForCalories, calories.toFloat()).apply()
                }
            }, INTERVAL
        )

        Log.d("kcal", calories.toString())
    }

    private fun calibrateLinearAcc(rawValue: FloatArray) {
        // uses the first 16 sensor values to calibrate linear acceleration sensor
        for (i in 0..15) {
            calibrationArray.add(rawValue)
        }
        calibrationArray.also {
            offsetValues[0] = getOffSetValue(it.map { xValues -> xValues[0] })
            offsetValues[1] = getOffSetValue(it.map { yValues -> yValues[1] })
            offsetValues[2] = getOffSetValue(it.map { yValues -> yValues[2] })
        }
        Log.d("offsets", "${offsetValues[0]}  ${offsetValues[1]}  ${offsetValues[2]}")
        isNotCalibrated = false
    }

    private  fun walkingAcceleration (values: FloatArray) {
        // acceleration data is collected every 10 seconds and the UI is updated
        val xLinearAcc = values[0] - offsetValues[0]
        val yLinearAcc = values[1] - offsetValues[1]
        val zLinearAcc = values[2] - offsetValues[2]
        Log.d("acc", "$xLinearAcc  $yLinearAcc  $zLinearAcc")
        magnitudeAcc =
            sqrt((xLinearAcc.pow(2) + yLinearAcc.pow(2) + zLinearAcc.pow(2)))
                .absoluteValue
        Log.d("magnitude",  magnitudeAcc.toString())

        mHandler.postDelayed(
            {   // handler ensures a 10 seconds interval between records
                sharedPref.edit().putString(dateForAcc, formatAcc()).apply()

                Log.d("acc_Delayed", "$xLinearAcc  $yLinearAcc  $zLinearAcc")
                Log.d("magnitude_Delayed",  magnitudeAcc.toString())
            }, 10000
        )
    }

    private fun getOffSetValue(calibrationValues: List<Float>): Float {
        // calculates the mean of the first 16 sensor values as offset
        val offset = calibrationValues.reduce{ acc, values -> acc + values }

        return offset/16
    }

    private fun formatAcc() : String {
        // truncates acc values to 2 decimal places
        val acc = magnitudeAcc.toString().substring(0..2)

        return if (acc.length == 3 && acc[2] == '.' ) {
            acc.substring(0..1)
        } else acc
    }

    private fun toast(text: String) {
        Toast.makeText(
            this, text, Toast.LENGTH_SHORT).show()
    }

    companion object{
        const val PREF = "com.prathameshkumbhar.bfit.homemodule.STEP_COUNTER"
        const val PREVIOUS_STEPS = "com.prathameshkumbhar.bfit.homemodule.PREVIOUS_STEPS"
        const val CHANNEL_ID = "com.prathameshkumbhar.bfit.homemodule.STEP_COUNTER_SERVICE"
        const val SERVICE_ID = 1
        const val STEP_COUNT_UPDATE = "com.prathameshkumbhar.bfit.homemodule.ACTION_STEPS"
        const val STEPS = "com.prathameshkumbhar.bfit.homemodule.MESSAGE_STEPS"
        const val DISTANCE_METER = "com.prathameshkumbhar.bfit.homemodule.DISTANCE_METER"
        const val MET = 3.5 // metabolic equivalent for moderate walking on a flat surface
        const val WEIGHT = "com.prathameshkumbhar.bfit.homemodule.USER_WEIGHT"
        const val INTERVAL = 60000L
    }
}