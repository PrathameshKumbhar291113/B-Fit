package com.prathameshkumbhar.bfit.onboardingmodule.fragment

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.droidman.ktoasty.KToasty
import com.prathameshkumbhar.bfit.databinding.FragmentBmiCalculatorBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.pow

class BmiCalculatorFragment : Fragment() {
    private var _binding : FragmentBmiCalculatorBinding? = null
    private val binding get() = _binding!!

    private val  navController by lazy {
        findNavController()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBmiCalculatorBinding.inflate(inflater, container,false)

        val sharePrefGender: SharedPreferences = context!!.getSharedPreferences("genderCheck", Context.MODE_PRIVATE)
        var checkGender = sharePrefGender.getBoolean("isMaleChecked",false)

        var maleDefaultHeight = 166
        var maleDefaultWeight = 63
        var femaleDefaultHeight = 155
        var femaleDefaultWeight = 50


        if (checkGender){
            binding.heightSeekbar.min = 87
            binding.heightSeekbar.progress = 165
            binding.showHeightText.text = maleDefaultHeight.toString()
            binding.showWeightText.text = maleDefaultWeight.toString()
        }else{
            binding.heightSeekbar.min = 85
            binding.heightSeekbar.progress = 152
            binding.showHeightText.text = femaleDefaultHeight.toString()
            binding.showWeightText.text = femaleDefaultWeight.toString()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.heightSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.showHeightText.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })



        binding.weightAddBtn.setOnClickListener{
            if (binding.showWeightText.text.toString().toInt()<130){
                binding.showWeightText.text = String.format("%s",binding.showWeightText.text.toString().toInt()+1)
            }else{
                KToasty.warning(requireContext(),"Maximum weight is 130 Kg!").show()
            }
        }

        binding.weightSubtractBtn.setOnClickListener {
            if(binding.showWeightText.text.toString().toInt()>12){
                binding.showWeightText.text = String.format("%s",binding.showWeightText.text.toString().toInt()-1)
            }else{
                KToasty.warning(requireContext(),"Minimum weight is 12 Kg!").show()
            }

        }

        binding.ageAddBtn.setOnClickListener {
            if (binding.showAgeText.text.toString().toInt()<100){

            }else{
                KToasty.warning(requireContext(),"Maximum Age is 100 Yr").show()
            }
            binding.showAgeText.text = String.format("%s",binding.showAgeText.text.toString().toInt()+1)
        }

        binding.ageSubtractBtn.setOnClickListener {
            if(binding.showAgeText.text.toString().toInt()>2){
                binding.showAgeText.text = String.format("%s",binding.showAgeText.text.toString().toInt()-1)
            }else{
                KToasty.warning(requireContext(),"Minimum Age is 2 Yr!").show()
            }
        }



        binding.submitButtonBmi.setOnClickListener {

            var weightForBMI = binding.showWeightText.text.toString().toInt()
            var heightForBMI = binding.showHeightText.text.toString().toInt()

//            var cmToMeter = heightForBMI * 0.01

            val numerator = weightForBMI.toFloat()
            val denominator = (heightForBMI.toFloat() * 0.01).pow(2.0)
            var calBmi = numerator/denominator
            var bmi = String.format("%.3f",calBmi)

            binding.bmiValueTV.visibility = View.VISIBLE
            binding.bmiValueTV.text = "$bmi Bmi"

            binding.bmiIndicatorTV.visibility = View.VISIBLE
            if(bmi< 15.toString()){
                binding.bmiIndicatorTV.text = "Very Severely Underweight"
                binding.bmiIndicatorTV.setTextColor(Color.parseColor("#e36414"))
            }else if(bmi >15.toString() && bmi < 16.toString()){
                binding.bmiIndicatorTV.text = "Severely Underweight"
                binding.bmiIndicatorTV.setTextColor(Color.parseColor("#f48c06"))
            }else if(bmi > 16.toString() && bmi <18.5.toString()){
                binding.bmiIndicatorTV.text = "Underweight"
                binding.bmiIndicatorTV.setTextColor(Color.parseColor("#ffba08"))
            }else if(bmi > 18.5.toString() && bmi < 25.toString()){
                binding.bmiIndicatorTV.text = "Normal Healthy"
                binding.bmiIndicatorTV.setTextColor(Color.parseColor("#2fbf71"))
            }else if(bmi >25.toString() && bmi < 30.toString()){
                binding.bmiIndicatorTV.text = "Overweight"
                binding.bmiIndicatorTV.setTextColor(Color.parseColor("#f94144"))
            }else if (bmi > 30.toString() && bmi < 35.toString()){
                binding.bmiIndicatorTV.text = "Moderately Obese"
                binding.bmiIndicatorTV.setTextColor(Color.parseColor("#e5383b"))
            }else if(bmi > 35.toString() && bmi < 40.toString()){
                binding.bmiIndicatorTV.text = "Severely Obese"
                binding.bmiIndicatorTV.setTextColor(Color.parseColor("#d7263d"))
            }else{
                binding.bmiIndicatorTV.text = "Very Severely Obese"
                binding.bmiIndicatorTV.setTextColor(Color.parseColor("#6a040f"))
            }

            binding.submitButtonBmi.visibility = View.GONE
            KToasty.info(requireContext(), "Your BMI has been submitted!").show()

            lifecycleScope.launch {
                delay(2500)
                binding.nextButtonBmi.visibility = View.VISIBLE
            }

            binding.ageAddBtn.isClickable = false
            binding.ageSubtractBtn.isClickable = false
            binding.weightAddBtn.isClickable = false
            binding.weightSubtractBtn.isClickable = false
            binding.heightSeekbar.isEnabled = false
        }

        binding.nextButtonBmi.setOnClickListener {
            navController.navigate(
                BmiCalculatorFragmentDirections.actionBmiCalculatorFragmentToSelectBodyTypeWorkoutFragment()
            )
        }

    }
}