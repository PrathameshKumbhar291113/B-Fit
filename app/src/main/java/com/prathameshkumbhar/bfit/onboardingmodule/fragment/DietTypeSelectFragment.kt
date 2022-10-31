package com.prathameshkumbhar.bfit.onboardingmodule.fragment

import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.droidman.ktoasty.KToasty
import com.prathameshkumbhar.bfit.databinding.FragmentDietTypeSelectBinding

class DietTypeSelectFragment : Fragment() {

    private var _binding : FragmentDietTypeSelectBinding? = null
    private val binding get() = _binding!!

    private val  navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDietTypeSelectBinding.inflate(inflater, container, false)

//        val sharePrefGender: SharedPreferences = context!!.getSharedPreferences("genderCheck", Context.MODE_PRIVATE)
//        var checkGender = sharePrefGender.getBoolean("isMaleChecked",false)
//        if (checkGender){
////            binding.getTonedTv.text = "Keep Fit"
//        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardVeg.setOnClickListener{

            binding.cardVeg.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardVeg.isChecked = true
            if (binding.cardVeg.isChecked){
                binding.cardVeg.strokeWidth = 8
            }

            binding.cardNonVeg.isChecked = false
            binding.cardMixDiet.isChecked = false

            binding.cardNonVeg.strokeWidth = 0
            binding.cardMixDiet.strokeWidth = 0

        }

        binding.cardNonVeg.setOnClickListener{

            binding.cardNonVeg.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardNonVeg.isChecked = true
            if (binding.cardNonVeg.isChecked){
                binding.cardNonVeg.strokeWidth = 8
            }

            binding.cardVeg.isChecked = false
            binding.cardMixDiet.isChecked = false

            binding.cardVeg.strokeWidth = 0
            binding.cardMixDiet.strokeWidth = 0

        }

        binding.cardMixDiet.setOnClickListener{

            binding.cardMixDiet.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardMixDiet.isChecked = true
            if (binding.cardMixDiet.isChecked){
                binding.cardMixDiet.strokeWidth = 8
            }

            binding.cardVeg.isChecked = false
            binding.cardNonVeg.isChecked = false


            binding.cardVeg.strokeWidth = 0
            binding.cardNonVeg.strokeWidth = 0
        }

        binding.nextButtonSelectDietType.setOnClickListener {

            if (binding.cardVeg.isChecked){

                isVegCardCheck()

                navController.navigate(
                    DietTypeSelectFragmentDirections.actionDietTypeSelectFragmentToSelectLevelOfExerciseFragment()
                )
            }else if (binding.cardNonVeg.isChecked){

                isNonVegCardCheck()

                navController.navigate(
                    DietTypeSelectFragmentDirections.actionDietTypeSelectFragmentToSelectLevelOfExerciseFragment()
                )
            } else if (binding.cardMixDiet.isChecked){

                isMixCardCheck()

                navController.navigate(
                    DietTypeSelectFragmentDirections.actionDietTypeSelectFragmentToSelectLevelOfExerciseFragment()
                )
            }else{
                KToasty.info(requireContext() ,"Kindly select the diet type!").show()

            }

        }
    }

    private fun isVegCardCheck(){
        val sharePrefVegDiet : SharedPreferences = context!!.getSharedPreferences(
            "cardVegChecked",
            Context.MODE_PRIVATE
        )
        var vegDietEditor : SharedPreferences.Editor = sharePrefVegDiet.edit()
        vegDietEditor.putBoolean("isVegCardCheck", true)
        vegDietEditor.apply()


        val sharePrefCardNonVeg : SharedPreferences = context!!.getSharedPreferences(
            "cardNonVegChecked",
            Context.MODE_PRIVATE
        )
        var nonVegEditor : SharedPreferences.Editor = sharePrefCardNonVeg.edit()
        nonVegEditor.putBoolean("isNonVegCardCheck", false)
        nonVegEditor.apply()


        val sharePrefMixedDiet : SharedPreferences = context!!.getSharedPreferences(
            "cardMixedDietChecked",
            Context.MODE_PRIVATE
        )
        var mixedDietEditor : SharedPreferences.Editor = sharePrefMixedDiet.edit()
        mixedDietEditor.putBoolean("isMixDietCardCheck", false)
        mixedDietEditor.apply()
    }

    private fun isNonVegCardCheck(){
        val sharePrefVegDiet : SharedPreferences = context!!.getSharedPreferences(
            "cardVegChecked",
            Context.MODE_PRIVATE
        )
        var vegDietEditor : SharedPreferences.Editor = sharePrefVegDiet.edit()
        vegDietEditor.putBoolean("isVegCardCheck", false)
        vegDietEditor.apply()


        val sharePrefCardNonVeg : SharedPreferences = context!!.getSharedPreferences(
            "cardNonVegChecked",
            Context.MODE_PRIVATE
        )
        var nonVegEditor : SharedPreferences.Editor = sharePrefCardNonVeg.edit()
        nonVegEditor.putBoolean("isNonVegCardCheck", true)
        nonVegEditor.apply()


        val sharePrefMixedDiet : SharedPreferences = context!!.getSharedPreferences(
            "cardMixedDietChecked",
            Context.MODE_PRIVATE
        )
        var mixedDietEditor : SharedPreferences.Editor = sharePrefMixedDiet.edit()
        mixedDietEditor.putBoolean("isMixDietCardCheck", false)
        mixedDietEditor.apply()

    }

    private fun isMixCardCheck(){

        val sharePrefVegDiet : SharedPreferences = context!!.getSharedPreferences(
            "cardVegChecked",
            Context.MODE_PRIVATE
        )
        var vegDietEditor : SharedPreferences.Editor = sharePrefVegDiet.edit()
        vegDietEditor.putBoolean("isVegCardCheck", false)
        vegDietEditor.apply()


        val sharePrefCardNonVeg : SharedPreferences = context!!.getSharedPreferences(
            "cardNonVegChecked",
            Context.MODE_PRIVATE
        )
        var nonVegEditor : SharedPreferences.Editor = sharePrefCardNonVeg.edit()
        nonVegEditor.putBoolean("isNonVegCardCheck", false)
        nonVegEditor.apply()


        val sharePrefMixedDiet : SharedPreferences = context!!.getSharedPreferences(
            "cardMixedDietChecked",
            Context.MODE_PRIVATE
        )
        var mixedDietEditor : SharedPreferences.Editor = sharePrefMixedDiet.edit()
        mixedDietEditor.putBoolean("isMixDietCardCheck", true)
        mixedDietEditor.apply()

    }

}