package com.prathameshkumbhar.bfit.mainmodule.activity

import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import com.prathameshkumbhar.bfit.coremodule.BaseActivity
import com.prathameshkumbhar.bfit.databinding.ActivityChangeDietPlanBinding


class ChangeDietPlanActivity : BaseActivity() {
    private lateinit var binding: ActivityChangeDietPlanBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChangeDietPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //loose wt veg card click listener
        binding.cardLooseWtVeg.setOnClickListener {

            binding.cardLooseWtVeg.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardLooseWtVeg.isChecked = true
            if (binding.cardLooseWtVeg.isChecked){
                binding.cardLooseWtVeg.strokeWidth = 8
            }
            binding.cardLooseWtNonVeg.isChecked = false
            binding.cardLooseWtMixed.isChecked = false

            binding.cardBuildMuscleVeg.isChecked = false
            binding.cardBuildMuscleNonVeg.isChecked = false
            binding.cardBuildMuscleMix.isChecked = false

            binding.cardBalanceVeg.isChecked = false
            binding.cardBalanceNonVeg.isChecked = false
            binding.cardBalanceMix.isChecked = false


            binding.cardLooseWtNonVeg.strokeWidth = 0
            binding.cardLooseWtMixed.strokeWidth = 0

            binding.cardBuildMuscleVeg.strokeWidth = 0
            binding.cardBuildMuscleNonVeg.strokeWidth = 0
            binding.cardBuildMuscleMix.strokeWidth = 0

            binding.cardBalanceVeg.strokeWidth = 0
            binding.cardBalanceNonVeg.strokeWidth = 0
            binding.cardBalanceMix.strokeWidth = 0

        }

        //loose wt non veg card click listener
        binding.cardLooseWtNonVeg.setOnClickListener {

            binding.cardLooseWtNonVeg.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardLooseWtNonVeg.isChecked = true
            if (binding.cardLooseWtNonVeg.isChecked){
                binding.cardLooseWtNonVeg.strokeWidth = 8
            }
            binding.cardLooseWtVeg.isChecked = false
            binding.cardLooseWtMixed.isChecked = false

            binding.cardBuildMuscleVeg.isChecked = false
            binding.cardBuildMuscleNonVeg.isChecked = false
            binding.cardBuildMuscleMix.isChecked = false

            binding.cardBalanceVeg.isChecked = false
            binding.cardBalanceNonVeg.isChecked = false
            binding.cardBalanceMix.isChecked = false

            binding.cardLooseWtVeg.strokeWidth = 0
            binding.cardLooseWtMixed.strokeWidth = 0

            binding.cardBuildMuscleVeg.strokeWidth = 0
            binding.cardBuildMuscleNonVeg.strokeWidth = 0
            binding.cardBuildMuscleMix.strokeWidth = 0

            binding.cardBalanceVeg.strokeWidth = 0
            binding.cardBalanceNonVeg.strokeWidth = 0
            binding.cardBalanceMix.strokeWidth = 0

        }

        //loose wt mix card click listener
        binding.cardLooseWtMixed.setOnClickListener {

            binding.cardLooseWtMixed.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardLooseWtMixed.isChecked = true
            if (binding.cardLooseWtMixed.isChecked){
                binding.cardLooseWtMixed.strokeWidth = 8
            }
            binding.cardLooseWtVeg.isChecked = false
            binding.cardLooseWtNonVeg.isChecked = false

            binding.cardBuildMuscleVeg.isChecked = false
            binding.cardBuildMuscleNonVeg.isChecked = false
            binding.cardBuildMuscleMix.isChecked = false

            binding.cardBalanceVeg.isChecked = false
            binding.cardBalanceNonVeg.isChecked = false
            binding.cardBalanceMix.isChecked = false

            binding.cardLooseWtVeg.strokeWidth = 0
            binding.cardLooseWtNonVeg.strokeWidth = 0

            binding.cardBuildMuscleVeg.strokeWidth = 0
            binding.cardBuildMuscleNonVeg.strokeWidth = 0
            binding.cardBuildMuscleMix.strokeWidth = 0

            binding.cardBalanceVeg.strokeWidth = 0
            binding.cardBalanceNonVeg.strokeWidth = 0
            binding.cardBalanceMix.strokeWidth = 0

        }

        //build wt veg card click listener
        binding.cardBuildMuscleVeg.setOnClickListener {

            binding.cardBuildMuscleVeg.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardBuildMuscleVeg.isChecked = true
            if (binding.cardBuildMuscleVeg.isChecked){
                binding.cardBuildMuscleVeg.strokeWidth = 8
            }
            binding.cardBuildMuscleNonVeg.isChecked = false
            binding.cardBuildMuscleMix.isChecked = false

            binding.cardLooseWtVeg.isChecked = false
            binding.cardLooseWtNonVeg.isChecked = false
            binding.cardLooseWtMixed.isChecked = false

            binding.cardBalanceVeg.isChecked = false
            binding.cardBalanceNonVeg.isChecked = false
            binding.cardBalanceMix.isChecked = false

            binding.cardLooseWtVeg.strokeWidth = 0
            binding.cardLooseWtNonVeg.strokeWidth = 0
            binding.cardLooseWtMixed.strokeWidth = 0

            binding.cardBuildMuscleNonVeg.strokeWidth = 0
            binding.cardBuildMuscleMix.strokeWidth = 0

            binding.cardBalanceVeg.strokeWidth = 0
            binding.cardBalanceNonVeg.strokeWidth = 0
            binding.cardBalanceMix.strokeWidth = 0
        }

        //build wt non veg card click listener
        binding.cardBuildMuscleNonVeg.setOnClickListener {

            binding.cardBuildMuscleNonVeg.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardBuildMuscleNonVeg.isChecked = true
            if (binding.cardBuildMuscleNonVeg.isChecked){
                binding.cardBuildMuscleNonVeg.strokeWidth = 8
            }
            binding.cardBuildMuscleVeg.isChecked = false
            binding.cardBuildMuscleMix.isChecked = false

            binding.cardLooseWtVeg.isChecked = false
            binding.cardLooseWtNonVeg.isChecked = false
            binding.cardLooseWtMixed.isChecked = false

            binding.cardBalanceVeg.isChecked = false
            binding.cardBalanceNonVeg.isChecked = false
            binding.cardBalanceMix.isChecked = false

            binding.cardLooseWtVeg.strokeWidth = 0
            binding.cardLooseWtNonVeg.strokeWidth = 0
            binding.cardLooseWtMixed.strokeWidth = 0

            binding.cardBuildMuscleVeg.strokeWidth = 0
            binding.cardBuildMuscleMix.strokeWidth = 0

            binding.cardBalanceVeg.strokeWidth = 0
            binding.cardBalanceNonVeg.strokeWidth = 0
            binding.cardBalanceMix.strokeWidth = 0
        }

        //build wt mix card click listener
        binding.cardBuildMuscleMix.setOnClickListener {

            binding.cardBuildMuscleMix.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardBuildMuscleMix.isChecked = true
            if (binding.cardBuildMuscleMix.isChecked){
                binding.cardBuildMuscleMix.strokeWidth = 8
            }
            binding.cardBuildMuscleVeg.isChecked = false
            binding.cardBuildMuscleNonVeg.isChecked = false

            binding.cardLooseWtVeg.isChecked = false
            binding.cardLooseWtNonVeg.isChecked = false
            binding.cardLooseWtMixed.isChecked = false

            binding.cardBalanceVeg.isChecked = false
            binding.cardBalanceNonVeg.isChecked = false
            binding.cardBalanceMix.isChecked = false

            binding.cardLooseWtVeg.strokeWidth = 0
            binding.cardLooseWtNonVeg.strokeWidth = 0
            binding.cardLooseWtMixed.strokeWidth = 0

            binding.cardBuildMuscleVeg.strokeWidth = 0
            binding.cardBuildMuscleNonVeg.strokeWidth = 0

            binding.cardBalanceVeg.strokeWidth = 0
            binding.cardBalanceNonVeg.strokeWidth = 0
            binding.cardBalanceMix.strokeWidth = 0
        }

        //balance diet veg card click listener
        binding.cardBalanceVeg.setOnClickListener {

            binding.cardBalanceVeg.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardBalanceVeg.isChecked = true
            if (binding.cardBalanceVeg.isChecked){
                binding.cardBalanceVeg.strokeWidth = 8
            }
            binding.cardBalanceNonVeg.isChecked = false
            binding.cardBalanceMix.isChecked = false

            binding.cardLooseWtVeg.isChecked = false
            binding.cardLooseWtNonVeg.isChecked = false
            binding.cardLooseWtMixed.isChecked = false

            binding.cardBuildMuscleVeg.isChecked = false
            binding.cardBuildMuscleNonVeg.isChecked = false
            binding.cardBuildMuscleMix.isChecked = false

            binding.cardLooseWtVeg.strokeWidth = 0
            binding.cardLooseWtNonVeg.strokeWidth = 0
            binding.cardLooseWtMixed.strokeWidth = 0

            binding.cardBuildMuscleVeg.strokeWidth = 0
            binding.cardBuildMuscleNonVeg.strokeWidth = 0
            binding.cardBuildMuscleMix.strokeWidth = 0

            binding.cardBalanceNonVeg.strokeWidth = 0
            binding.cardBalanceMix.strokeWidth = 0
        }

        //balance diet non veg card click listener
        binding.cardBalanceNonVeg.setOnClickListener {

            binding.cardBalanceNonVeg.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardBalanceNonVeg.isChecked = true
            if(binding.cardBalanceNonVeg.isChecked){
                binding.cardBalanceNonVeg.strokeWidth = 8
            }
            binding.cardBalanceVeg.isChecked = false
            binding.cardBalanceMix.isChecked = false

            binding.cardLooseWtVeg.isChecked = false
            binding.cardLooseWtNonVeg.isChecked = false
            binding.cardLooseWtMixed.isChecked = false

            binding.cardBuildMuscleVeg.isChecked = false
            binding.cardBuildMuscleNonVeg.isChecked = false
            binding.cardBuildMuscleMix.isChecked = false

            binding.cardLooseWtVeg.strokeWidth = 0
            binding.cardLooseWtNonVeg.strokeWidth = 0
            binding.cardLooseWtMixed.strokeWidth = 0

            binding.cardBuildMuscleVeg.strokeWidth = 0
            binding.cardBuildMuscleNonVeg.strokeWidth = 0
            binding.cardBuildMuscleMix.strokeWidth = 0

            binding.cardBalanceVeg.strokeWidth = 0
            binding.cardBalanceMix.strokeWidth = 0
        }

        //balance diet mix card click listener
        binding.cardBalanceMix.setOnClickListener{

            binding.cardBalanceMix.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardBalanceMix.isChecked = true
            if(binding.cardBalanceMix.isChecked){
                binding.cardBalanceMix.strokeWidth = 8
            }
            binding.cardBalanceVeg.isChecked = false
            binding.cardBalanceNonVeg.isChecked = false

            binding.cardLooseWtVeg.isChecked = false
            binding.cardLooseWtNonVeg.isChecked = false
            binding.cardLooseWtMixed.isChecked = false

            binding.cardBuildMuscleVeg.isChecked = false
            binding.cardBuildMuscleNonVeg.isChecked = false
            binding.cardBuildMuscleMix.isChecked = false

            binding.cardLooseWtVeg.strokeWidth = 0
            binding.cardLooseWtNonVeg.strokeWidth = 0
            binding.cardLooseWtMixed.strokeWidth = 0

            binding.cardBuildMuscleVeg.strokeWidth = 0
            binding.cardBuildMuscleNonVeg.strokeWidth = 0
            binding.cardBuildMuscleMix.strokeWidth = 0

            binding.cardBalanceVeg.strokeWidth = 0
            binding.cardBalanceNonVeg.strokeWidth = 0
        }


        //button click listener
        binding.changeDietSubmitBtn.setOnClickListener {

            if (binding.cardLooseWtVeg.isChecked){
                looseWtVegCardCheck()
            }else if (binding.cardLooseWtNonVeg.isChecked){
                looseWtNonVegCardCheck()
            }else if (binding.cardLooseWtMixed.isChecked){
                looseWtMixCardCheck()
            }else if (binding.cardBuildMuscleVeg.isChecked){
                buildWtVegCardCheck()
            }else if (binding.cardBuildMuscleNonVeg.isChecked){
                buildWtNonVegCardCheck()
            }else if (binding.cardBuildMuscleMix.isChecked){
                buildWtMixCardCheck()
            }else if (binding.cardBalanceVeg.isChecked){
                balanceDietVegCardCheck()
            }else if (binding.cardBalanceNonVeg.isChecked){
                balanceDietNonVegCardCheck()
            }else if (binding.cardBalanceMix.isChecked){
                balanceDietMixCardCheck()
            }



        }
    }



    private fun looseWtVegCardCheck(){

        //LooseWt card check
        val sharePrefLooseWeight : SharedPreferences = getSharedPreferences(
            "cardLooseWeightChecked",
            Context.MODE_PRIVATE
        )
        var looseWeightEditor : SharedPreferences.Editor = sharePrefLooseWeight.edit()
        looseWeightEditor.putBoolean("isLooseWeightCardCheck", true)
        looseWeightEditor.apply()

        //veg card check
        val sharePrefVegDiet : SharedPreferences = getSharedPreferences(
            "cardVegChecked",
            Context.MODE_PRIVATE
        )
        var vegDietEditor : SharedPreferences.Editor = sharePrefVegDiet.edit()
        vegDietEditor.putBoolean("isVegCardCheck", true)
        vegDietEditor.apply()

        //un-check cards diet type
        val sharePrefCardNonVeg : SharedPreferences = getSharedPreferences(
            "cardNonVegChecked",
            Context.MODE_PRIVATE
        )
        var nonVegEditor : SharedPreferences.Editor = sharePrefCardNonVeg.edit()
        nonVegEditor.clear()
        nonVegEditor.apply()


        val sharePrefMixedDiet : SharedPreferences = getSharedPreferences(
            "cardMixedDietChecked",
            Context.MODE_PRIVATE
        )
        var mixedDietEditor : SharedPreferences.Editor = sharePrefMixedDiet.edit()
        mixedDietEditor.clear()
        mixedDietEditor.apply()

        //un check diet goal type
        val sharePrefBuildMuscle : SharedPreferences = getSharedPreferences(
            "cardBuildMuscleChecked",
            Context.MODE_PRIVATE
        )
        var buildMuscleEditor : SharedPreferences.Editor = sharePrefBuildMuscle.edit()
        buildMuscleEditor.clear()
        buildMuscleEditor.apply()


        val sharePrefBalance : SharedPreferences = getSharedPreferences(
            "cardBalanceChecked",
            Context.MODE_PRIVATE
        )
        var balanceEditor : SharedPreferences.Editor = sharePrefBalance.edit()
        balanceEditor.clear()
        balanceEditor.apply()

    }

    private fun looseWtNonVegCardCheck(){

        //LooseWt card check
        val sharePrefLooseWeight : SharedPreferences = getSharedPreferences(
            "cardLooseWeightChecked",
            Context.MODE_PRIVATE
        )
        var looseWeightEditor : SharedPreferences.Editor = sharePrefLooseWeight.edit()
        looseWeightEditor.putBoolean("isLooseWeightCardCheck", true)
        looseWeightEditor.apply()

        //Non veg card check
        val sharePrefCardNonVeg : SharedPreferences = getSharedPreferences(
            "cardNonVegChecked",
            Context.MODE_PRIVATE
        )
        var nonVegEditor : SharedPreferences.Editor = sharePrefCardNonVeg.edit()
        nonVegEditor.putBoolean("isNonVegCardCheck", true)
        nonVegEditor.apply()

        //un-check cards diet type
        val sharePrefVegDiet : SharedPreferences = getSharedPreferences(
            "cardVegChecked",
            Context.MODE_PRIVATE
        )
        var vegDietEditor : SharedPreferences.Editor = sharePrefVegDiet.edit()
        vegDietEditor.clear()
        vegDietEditor.apply()

        val sharePrefMixedDiet : SharedPreferences = getSharedPreferences(
            "cardMixedDietChecked",
            Context.MODE_PRIVATE
        )
        var mixedDietEditor : SharedPreferences.Editor = sharePrefMixedDiet.edit()
        mixedDietEditor.clear()
        mixedDietEditor.apply()

        //un check diet goal type
        val sharePrefBuildMuscle : SharedPreferences = getSharedPreferences(
            "cardBuildMuscleChecked",
            Context.MODE_PRIVATE
        )
        var buildMuscleEditor : SharedPreferences.Editor = sharePrefBuildMuscle.edit()
        buildMuscleEditor.clear()
        buildMuscleEditor.apply()


        val sharePrefBalance : SharedPreferences = getSharedPreferences(
            "cardBalanceChecked",
            Context.MODE_PRIVATE
        )
        var balanceEditor : SharedPreferences.Editor = sharePrefBalance.edit()
        balanceEditor.clear()
        balanceEditor.apply()
    }

    private fun looseWtMixCardCheck(){

        //LooseWt card check
        val sharePrefLooseWeight : SharedPreferences = getSharedPreferences(
            "cardLooseWeightChecked",
            Context.MODE_PRIVATE
        )
        var looseWeightEditor : SharedPreferences.Editor = sharePrefLooseWeight.edit()
        looseWeightEditor.putBoolean("isLooseWeightCardCheck", true)
        looseWeightEditor.apply()

        //mix diet type card check
        val sharePrefMixedDiet : SharedPreferences = getSharedPreferences(
            "cardMixedDietChecked",
            Context.MODE_PRIVATE
        )
        var mixedDietEditor : SharedPreferences.Editor = sharePrefMixedDiet.edit()
        mixedDietEditor.putBoolean("isMixDietCardCheck", true)
        mixedDietEditor.apply()

        //un-check cards diet type
        val sharePrefVegDiet : SharedPreferences = getSharedPreferences(
            "cardVegChecked",
            Context.MODE_PRIVATE
        )
        var vegDietEditor : SharedPreferences.Editor = sharePrefVegDiet.edit()
        vegDietEditor.clear()
        vegDietEditor.apply()

        val sharePrefCardNonVeg : SharedPreferences = getSharedPreferences(
            "cardNonVegChecked",
            Context.MODE_PRIVATE
        )
        var nonVegEditor : SharedPreferences.Editor = sharePrefCardNonVeg.edit()
        nonVegEditor.clear()
        nonVegEditor.apply()

        //un check diet goal type
        val sharePrefBuildMuscle : SharedPreferences = getSharedPreferences(
            "cardBuildMuscleChecked",
            Context.MODE_PRIVATE
        )
        var buildMuscleEditor : SharedPreferences.Editor = sharePrefBuildMuscle.edit()
        buildMuscleEditor.clear()
        buildMuscleEditor.apply()


        val sharePrefBalance : SharedPreferences = getSharedPreferences(
            "cardBalanceChecked",
            Context.MODE_PRIVATE
        )
        var balanceEditor : SharedPreferences.Editor = sharePrefBalance.edit()
        balanceEditor.clear()
        balanceEditor.apply()

    }

    private fun buildWtVegCardCheck(){

        //buildWt card check
        val sharePrefBuildMuscle : SharedPreferences = getSharedPreferences(
            "cardBuildMuscleChecked",
            Context.MODE_PRIVATE
        )
        var buildMuscleEditor : SharedPreferences.Editor = sharePrefBuildMuscle.edit()
        buildMuscleEditor.putBoolean("isBuildMuscleCardCheck", true)
        buildMuscleEditor.apply()

        //veg card check
        val sharePrefVegDiet : SharedPreferences = getSharedPreferences(
            "cardVegChecked",
            Context.MODE_PRIVATE
        )
        var vegDietEditor : SharedPreferences.Editor = sharePrefVegDiet.edit()
        vegDietEditor.putBoolean("isVegCardCheck", true)
        vegDietEditor.apply()

        //un-check cards diet type
        val sharePrefCardNonVeg : SharedPreferences = getSharedPreferences(
            "cardNonVegChecked",
            Context.MODE_PRIVATE
        )
        var nonVegEditor : SharedPreferences.Editor = sharePrefCardNonVeg.edit()
        nonVegEditor.clear()
        nonVegEditor.apply()


        val sharePrefMixedDiet : SharedPreferences = getSharedPreferences(
            "cardMixedDietChecked",
            Context.MODE_PRIVATE
        )
        var mixedDietEditor : SharedPreferences.Editor = sharePrefMixedDiet.edit()
        mixedDietEditor.clear()
        mixedDietEditor.apply()


        //un check diet goal type
        val sharePrefLooseWeight : SharedPreferences = getSharedPreferences(
            "cardLooseWeightChecked",
            Context.MODE_PRIVATE
        )
        var looseWeightEditor : SharedPreferences.Editor = sharePrefLooseWeight.edit()
        looseWeightEditor.clear()
        looseWeightEditor.apply()

        val sharePrefBalance : SharedPreferences = getSharedPreferences(
            "cardBalanceChecked",
            Context.MODE_PRIVATE
        )
        var balanceEditor : SharedPreferences.Editor = sharePrefBalance.edit()
        balanceEditor.clear()
        balanceEditor.apply()

    }

    private fun buildWtNonVegCardCheck(){
        //buildWt card check
        val sharePrefBuildMuscle : SharedPreferences = getSharedPreferences(
            "cardBuildMuscleChecked",
            Context.MODE_PRIVATE
        )
        var buildMuscleEditor : SharedPreferences.Editor = sharePrefBuildMuscle.edit()
        buildMuscleEditor.putBoolean("isBuildMuscleCardCheck", true)
        buildMuscleEditor.apply()

        //Non veg card check
        val sharePrefCardNonVeg : SharedPreferences = getSharedPreferences(
            "cardNonVegChecked",
            Context.MODE_PRIVATE
        )
        var nonVegEditor : SharedPreferences.Editor = sharePrefCardNonVeg.edit()
        nonVegEditor.putBoolean("isNonVegCardCheck", true)
        nonVegEditor.apply()


        //un-check cards diet type
        val sharePrefVegDiet : SharedPreferences = getSharedPreferences(
            "cardVegChecked",
            Context.MODE_PRIVATE
        )
        var vegDietEditor : SharedPreferences.Editor = sharePrefVegDiet.edit()
        vegDietEditor.clear()
        vegDietEditor.apply()

        val sharePrefMixedDiet : SharedPreferences = getSharedPreferences(
            "cardMixedDietChecked",
            Context.MODE_PRIVATE
        )
        var mixedDietEditor : SharedPreferences.Editor = sharePrefMixedDiet.edit()
        mixedDietEditor.clear()
        mixedDietEditor.apply()


        //un check diet goal type
        val sharePrefLooseWeight : SharedPreferences = getSharedPreferences(
            "cardLooseWeightChecked",
            Context.MODE_PRIVATE
        )
        var looseWeightEditor : SharedPreferences.Editor = sharePrefLooseWeight.edit()
        looseWeightEditor.clear()
        looseWeightEditor.apply()

        val sharePrefBalance : SharedPreferences = getSharedPreferences(
            "cardBalanceChecked",
            Context.MODE_PRIVATE
        )
        var balanceEditor : SharedPreferences.Editor = sharePrefBalance.edit()
        balanceEditor.clear()
        balanceEditor.apply()

    }

    private fun buildWtMixCardCheck(){

        //buildWt card check
        val sharePrefBuildMuscle : SharedPreferences = getSharedPreferences(
            "cardBuildMuscleChecked",
            Context.MODE_PRIVATE
        )
        var buildMuscleEditor : SharedPreferences.Editor = sharePrefBuildMuscle.edit()
        buildMuscleEditor.putBoolean("isBuildMuscleCardCheck", true)
        buildMuscleEditor.apply()

        //mix diet type card check
        val sharePrefMixedDiet : SharedPreferences = getSharedPreferences(
            "cardMixedDietChecked",
            Context.MODE_PRIVATE
        )
        var mixedDietEditor : SharedPreferences.Editor = sharePrefMixedDiet.edit()
        mixedDietEditor.putBoolean("isMixDietCardCheck", true)
        mixedDietEditor.apply()


        //un-check cards diet type
        val sharePrefVegDiet : SharedPreferences = getSharedPreferences(
            "cardVegChecked",
            Context.MODE_PRIVATE
        )
        var vegDietEditor : SharedPreferences.Editor = sharePrefVegDiet.edit()
        vegDietEditor.clear()
        vegDietEditor.apply()

        val sharePrefCardNonVeg : SharedPreferences = getSharedPreferences(
            "cardNonVegChecked",
            Context.MODE_PRIVATE
        )
        var nonVegEditor : SharedPreferences.Editor = sharePrefCardNonVeg.edit()
        nonVegEditor.clear()
        nonVegEditor.apply()


        //un check diet goal type
        val sharePrefLooseWeight : SharedPreferences = getSharedPreferences(
            "cardLooseWeightChecked",
            Context.MODE_PRIVATE
        )
        var looseWeightEditor : SharedPreferences.Editor = sharePrefLooseWeight.edit()
        looseWeightEditor.clear()
        looseWeightEditor.apply()

        val sharePrefBalance : SharedPreferences = getSharedPreferences(
            "cardBalanceChecked",
            Context.MODE_PRIVATE
        )
        var balanceEditor : SharedPreferences.Editor = sharePrefBalance.edit()
        balanceEditor.clear()
        balanceEditor.apply()

    }

    private fun balanceDietVegCardCheck(){

        // balance diet card check
        val sharePrefBalance : SharedPreferences = getSharedPreferences(
            "cardBalanceChecked",
            Context.MODE_PRIVATE
        )
        var balanceEditor : SharedPreferences.Editor = sharePrefBalance.edit()
        balanceEditor.putBoolean("isBalanceCardCheck", true)
        balanceEditor.apply()

        //veg card check
        val sharePrefVegDiet : SharedPreferences = getSharedPreferences(
            "cardVegChecked",
            Context.MODE_PRIVATE
        )
        var vegDietEditor : SharedPreferences.Editor = sharePrefVegDiet.edit()
        vegDietEditor.putBoolean("isVegCardCheck", true)
        vegDietEditor.apply()


        //un-check cards diet type
        val sharePrefCardNonVeg : SharedPreferences = getSharedPreferences(
            "cardNonVegChecked",
            Context.MODE_PRIVATE
        )
        var nonVegEditor : SharedPreferences.Editor = sharePrefCardNonVeg.edit()
        nonVegEditor.clear()
        nonVegEditor.apply()

        val sharePrefMixedDiet : SharedPreferences = getSharedPreferences(
            "cardMixedDietChecked",
            Context.MODE_PRIVATE
        )
        var mixedDietEditor : SharedPreferences.Editor = sharePrefMixedDiet.edit()
        mixedDietEditor.clear()
        mixedDietEditor.apply()


        //un check diet goal type
        val sharePrefLooseWeight : SharedPreferences = getSharedPreferences(
            "cardLooseWeightChecked",
            Context.MODE_PRIVATE
        )
        var looseWeightEditor : SharedPreferences.Editor = sharePrefLooseWeight.edit()
        looseWeightEditor.clear()
        looseWeightEditor.apply()

        val sharePrefBuildMuscle : SharedPreferences = getSharedPreferences(
            "cardBuildMuscleChecked",
            Context.MODE_PRIVATE
        )
        var buildMuscleEditor : SharedPreferences.Editor = sharePrefBuildMuscle.edit()
        buildMuscleEditor.clear()
        buildMuscleEditor.apply()

    }

    private fun balanceDietNonVegCardCheck(){
        // balance diet card check
        val sharePrefBalance : SharedPreferences = getSharedPreferences(
            "cardBalanceChecked",
            Context.MODE_PRIVATE
        )
        var balanceEditor : SharedPreferences.Editor = sharePrefBalance.edit()
        balanceEditor.putBoolean("isBalanceCardCheck", true)
        balanceEditor.apply()

        //Non veg card check
        val sharePrefCardNonVeg : SharedPreferences = getSharedPreferences(
            "cardNonVegChecked",
            Context.MODE_PRIVATE
        )
        var nonVegEditor : SharedPreferences.Editor = sharePrefCardNonVeg.edit()
        nonVegEditor.putBoolean("isNonVegCardCheck", true)
        nonVegEditor.apply()


        //un-check cards diet type
        val sharePrefVegDiet : SharedPreferences = getSharedPreferences(
            "cardVegChecked",
            Context.MODE_PRIVATE
        )
        var vegDietEditor : SharedPreferences.Editor = sharePrefVegDiet.edit()
        vegDietEditor.clear()
        vegDietEditor.apply()

        val sharePrefMixedDiet : SharedPreferences = getSharedPreferences(
            "cardMixedDietChecked",
            Context.MODE_PRIVATE
        )
        var mixedDietEditor : SharedPreferences.Editor = sharePrefMixedDiet.edit()
        mixedDietEditor.clear()
        mixedDietEditor.apply()


        //un check diet goal type
        val sharePrefLooseWeight : SharedPreferences = getSharedPreferences(
            "cardLooseWeightChecked",
            Context.MODE_PRIVATE
        )
        var looseWeightEditor : SharedPreferences.Editor = sharePrefLooseWeight.edit()
        looseWeightEditor.clear()
        looseWeightEditor.apply()

        val sharePrefBuildMuscle : SharedPreferences = getSharedPreferences(
            "cardBuildMuscleChecked",
            Context.MODE_PRIVATE
        )
        var buildMuscleEditor : SharedPreferences.Editor = sharePrefBuildMuscle.edit()
        buildMuscleEditor.clear()
        buildMuscleEditor.apply()
    }

    private fun balanceDietMixCardCheck(){
        // balance diet card check
        val sharePrefBalance : SharedPreferences = getSharedPreferences(
            "cardBalanceChecked",
            Context.MODE_PRIVATE
        )
        var balanceEditor : SharedPreferences.Editor = sharePrefBalance.edit()
        balanceEditor.putBoolean("isBalanceCardCheck", true)
        balanceEditor.apply()

        //mix diet type card check
        val sharePrefMixedDiet : SharedPreferences = getSharedPreferences(
            "cardMixedDietChecked",
            Context.MODE_PRIVATE
        )
        var mixedDietEditor : SharedPreferences.Editor = sharePrefMixedDiet.edit()
        mixedDietEditor.putBoolean("isMixDietCardCheck", true)
        mixedDietEditor.apply()


        //un-check cards diet type
        val sharePrefVegDiet : SharedPreferences = getSharedPreferences(
            "cardVegChecked",
            Context.MODE_PRIVATE
        )
        var vegDietEditor : SharedPreferences.Editor = sharePrefVegDiet.edit()
        vegDietEditor.clear()
        vegDietEditor.apply()

        val sharePrefCardNonVeg : SharedPreferences = getSharedPreferences(
            "cardNonVegChecked",
            Context.MODE_PRIVATE
        )
        var nonVegEditor : SharedPreferences.Editor = sharePrefCardNonVeg.edit()
        nonVegEditor.clear()
        nonVegEditor.apply()


        //un check diet goal type
        val sharePrefLooseWeight : SharedPreferences = getSharedPreferences(
            "cardLooseWeightChecked",
            Context.MODE_PRIVATE
        )
        var looseWeightEditor : SharedPreferences.Editor = sharePrefLooseWeight.edit()
        looseWeightEditor.clear()
        looseWeightEditor.apply()

        val sharePrefBuildMuscle : SharedPreferences = getSharedPreferences(
            "cardBuildMuscleChecked",
            Context.MODE_PRIVATE
        )
        var buildMuscleEditor : SharedPreferences.Editor = sharePrefBuildMuscle.edit()
        buildMuscleEditor.clear()
        buildMuscleEditor.apply()

    }

}