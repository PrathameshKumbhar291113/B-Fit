package com.prathameshkumbhar.bfit.homemodule.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

open class StepsReceiver() : BroadcastReceiver() {
    var steps = 0
    override fun onReceive(context: Context, intent: Intent) {
        steps = intent.getIntExtra(StepCounterService.STEPS, 0)

        Log.d("stepsUI", steps.toString())
    }
}