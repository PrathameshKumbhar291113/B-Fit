package com.prathameshkumbhar.bfit.onboardingmodule.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.droidman.ktoasty.KToasty
import com.prathameshkumbhar.bfit.databinding.FragmentSelectLevelOfExerciseBinding

class SelectLevelOfExerciseFragment : Fragment() {
    private var _binding : FragmentSelectLevelOfExerciseBinding? = null
    private val binding get() = _binding!!

    private val  navController by lazy {
        findNavController()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSelectLevelOfExerciseBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardBeginner.setOnClickListener {
            binding.cardBeginner.isChecked = !binding.cardBeginner.isChecked

            binding.cardBeginner.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardBeginner.strokeWidth = 8
            binding.cardIntermediate.strokeWidth = 0
            binding.cardAdvanced.strokeWidth = 0

            if(!binding.cardBeginner.isChecked){
                binding.cardBeginner.strokeWidth = 0
            }

            binding.cardIntermediate.isChecked = false
            binding.cardAdvanced.isChecked = false
        }

        binding.cardIntermediate.setOnClickListener {
            binding.cardIntermediate.isChecked = !binding.cardIntermediate.isChecked

            binding.cardIntermediate.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardBeginner.strokeWidth = 0
            binding.cardIntermediate.strokeWidth = 8
            binding.cardAdvanced.strokeWidth = 0

            if(!binding.cardIntermediate.isChecked){
                binding.cardIntermediate.strokeWidth = 0
            }

            binding.cardBeginner.isChecked = false
            binding.cardAdvanced.isChecked = false
        }

        binding.cardAdvanced.setOnClickListener {
            binding.cardAdvanced.isChecked = !binding.cardAdvanced.isChecked

            binding.cardAdvanced.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardBeginner.strokeWidth = 0
            binding.cardIntermediate.strokeWidth = 0
            binding.cardAdvanced.strokeWidth = 8

            if(!binding.cardAdvanced.isChecked){
                binding.cardAdvanced.strokeWidth = 0
            }

            binding.cardIntermediate.isChecked = false
            binding.cardBeginner.isChecked = false
        }

        binding.nextButtonSelectLevelOfExercise.setOnClickListener {
            if(binding.cardBeginner.isChecked || binding.cardIntermediate.isChecked || binding.cardAdvanced.isChecked){
                navController.navigate(
                    SelectLevelOfExerciseFragmentDirections.actionSelectLevelOfExerciseFragmentToPersonActivityStatusFragment()
                )
            }else{
                KToasty.info(requireContext(),"Kindly select your level!").show()
            }

        }

    }
}