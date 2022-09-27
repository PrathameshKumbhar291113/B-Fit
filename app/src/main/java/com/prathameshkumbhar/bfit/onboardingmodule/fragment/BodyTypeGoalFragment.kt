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
import com.prathameshkumbhar.bfit.databinding.FragmentBodyTypeGoalBinding

class BodyTypeGoalFragment : Fragment() {

    private var _binding : FragmentBodyTypeGoalBinding? = null
    private val binding get() = _binding!!

    private val  navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBodyTypeGoalBinding.inflate(inflater, container, false)

        val sharePrefGender: SharedPreferences = context!!.getSharedPreferences("genderCheck", Context.MODE_PRIVATE)
        var checkGender = sharePrefGender.getBoolean("isMaleChecked",false)
        if (checkGender){
            binding.getTonedTv.text = "Keep Fit"
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardLooseWeight.setOnClickListener{

            val sharePrefLooseWeight : SharedPreferences = context!!.getSharedPreferences("cardLooseWeightChecked",Context.MODE_PRIVATE)
            var looseWeightEditor : SharedPreferences.Editor = sharePrefLooseWeight.edit()
            looseWeightEditor.putBoolean("isLooseWeightCardCheck", true)
            looseWeightEditor.apply()

            binding.cardLooseWeight.isChecked = !binding.cardLooseWeight.isChecked

            binding.cardLooseWeight.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardLooseWeight.strokeWidth = 8
            binding.cardBuildMuscle.strokeWidth = 0
            binding.cardGetToned.strokeWidth = 0

            binding.cardBuildMuscle.isChecked = false
            binding.cardGetToned.isChecked = false

            if (!binding.cardLooseWeight.isChecked){
                binding.cardLooseWeight.strokeWidth = 0
            }
        }

        binding.cardBuildMuscle.setOnClickListener{
            binding.cardBuildMuscle.isChecked = !binding.cardBuildMuscle.isChecked

            binding.cardBuildMuscle.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardLooseWeight.strokeWidth = 0
            binding.cardBuildMuscle.strokeWidth = 8
            binding.cardGetToned.strokeWidth = 0

            binding.cardLooseWeight.isChecked = false
            binding.cardGetToned.isChecked = false

            if (!binding.cardBuildMuscle.isChecked){
                binding.cardBuildMuscle.strokeWidth = 0
            }
        }

        binding.cardGetToned.setOnClickListener{
            binding.cardGetToned.isChecked = !binding.cardGetToned.isChecked

            binding.cardGetToned.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardLooseWeight.strokeWidth = 0
            binding.cardBuildMuscle.strokeWidth = 0
            binding.cardGetToned.strokeWidth = 8

            binding.cardLooseWeight.isChecked = false
            binding.cardBuildMuscle.isChecked = false

            if (!binding.cardGetToned.isChecked){
                binding.cardGetToned.strokeWidth = 0
            }
        }

        binding.nextButtonSelectBodyGoal.setOnClickListener {

            if (binding.cardLooseWeight.isChecked || binding.cardBuildMuscle.isChecked || binding.cardGetToned.isChecked){

                navController.navigate(
                    BodyTypeGoalFragmentDirections.actionBodyTypeGoalFragmentToSelectLevelOfExerciseFragment()
                )
            }else{
                KToasty.info(requireContext() ,"Kindly select the goal to get the plan!").show()

            }

        }
    }

}