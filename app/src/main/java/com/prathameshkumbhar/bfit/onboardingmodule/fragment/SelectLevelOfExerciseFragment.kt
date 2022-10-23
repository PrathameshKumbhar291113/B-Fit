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

        //Selecting the beginner card
        binding.cardBeginner.setOnClickListener {

            binding.cardBeginner.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardBeginner.isChecked = true
            if(binding.cardBeginner.isChecked){
                binding.cardBeginner.strokeWidth = 8
            }

            binding.cardIntermediate.isChecked = false
            binding.cardAdvanced.isChecked = false

            binding.cardIntermediate.strokeWidth = 0
            binding.cardAdvanced.strokeWidth = 0
        }


        //Selecting the Intermediate card
        binding.cardIntermediate.setOnClickListener {

            binding.cardIntermediate.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardIntermediate.isChecked = true
            if(binding.cardIntermediate.isChecked){
                binding.cardIntermediate.strokeWidth = 8
            }

            binding.cardBeginner.isChecked = false
            binding.cardAdvanced.isChecked = false

            binding.cardBeginner.strokeWidth = 0
            binding.cardAdvanced.strokeWidth = 0
        }

        //Selecting the Advance card
        binding.cardAdvanced.setOnClickListener {

            binding.cardAdvanced.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardAdvanced.isChecked = true
            if(binding.cardAdvanced.isChecked){
                binding.cardAdvanced.strokeWidth = 8
            }

            binding.cardIntermediate.isChecked = false
            binding.cardBeginner.isChecked = false

            binding.cardBeginner.strokeWidth = 0
            binding.cardIntermediate.strokeWidth = 0
        }

        binding.nextButtonSelectLevelOfExercise.setOnClickListener {
            if(binding.cardBeginner.isChecked ){

                val sharePrefBeginner : SharedPreferences = context!!.getSharedPreferences("cardBeginner", Context.MODE_PRIVATE)
                var beginnerEditor : SharedPreferences.Editor = sharePrefBeginner.edit()
                beginnerEditor.putBoolean("isBeginnerCardCheck", true)
                beginnerEditor.apply()

                navController.navigate(
                    SelectLevelOfExerciseFragmentDirections.actionSelectLevelOfExerciseFragmentToPersonActivityStatusFragment()
                )

            }else if (binding.cardIntermediate.isChecked ){

                val sharePrefIntermediate : SharedPreferences = context!!.getSharedPreferences("cardIntermediate", Context.MODE_PRIVATE)
                var intermediateEditor : SharedPreferences.Editor = sharePrefIntermediate.edit()
                intermediateEditor.putBoolean("isIntermediateCardCheck", true)
                intermediateEditor.apply()

                navController.navigate(
                    SelectLevelOfExerciseFragmentDirections.actionSelectLevelOfExerciseFragmentToPersonActivityStatusFragment()
                )

            }else if (binding.cardAdvanced.isChecked){

                val sharePrefAdvance : SharedPreferences = context!!.getSharedPreferences("cardAdvance", Context.MODE_PRIVATE)
                var advanceEditor : SharedPreferences.Editor = sharePrefAdvance.edit()
                advanceEditor.putBoolean("isAdvanceCardCheck", true)
                advanceEditor.apply()

                navController.navigate(
                    SelectLevelOfExerciseFragmentDirections.actionSelectLevelOfExerciseFragmentToPersonActivityStatusFragment()
                )

            }else{
                KToasty.info(requireContext(),"Kindly select your level!").show()
            }

        }

    }
}