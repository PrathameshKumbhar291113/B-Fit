package com.prathameshkumbhar.bfit.onboardingmodule.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.prathameshkumbhar.bfit.R
import com.prathameshkumbhar.bfit.databinding.FragmentSelectBodyPartWorkoutBinding
import splitties.views.padding


class SelectBodyPartWorkoutFragment : Fragment() {
    private var _binding : FragmentSelectBodyPartWorkoutBinding? = null
    private val binding get() = _binding!!

    private val  navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSelectBodyPartWorkoutBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharePrefGender: SharedPreferences = context!!.getSharedPreferences("genderCheck", Context.MODE_PRIVATE)
        var checkGender = sharePrefGender.getBoolean("isMaleChecked",false)
        if (checkGender){
            binding.genderShowImg.load(R.drawable.male)
            binding.genderShowImg.scaleType = ImageView.ScaleType.CENTER_CROP
            binding.genderShowImg.padding = 10
        }else{
            binding.genderShowImg.load(R.drawable.female)
            binding.genderShowImg.scaleType = ImageView.ScaleType.CENTER_CROP
            binding.genderShowImg.paddingStart
            binding.genderShowImg.paddingEnd
            binding.genderShowImg.paddingTop
            binding.genderShowImg.paddingBottom
        }

        binding.cardFullBody.setOnClickListener {
            binding.cardFullBody.isChecked = !binding.cardFullBody.isChecked
            if(binding.cardFullBody.isChecked){

                binding.cardArm.isChecked = false
                binding.cardAbs.isChecked = false
                binding.cardLeg.isChecked = false
            }
        }

        binding.cardArm.setOnClickListener {
            binding.cardArm.isChecked = !binding.cardArm.isChecked
            binding.cardFullBody.isChecked = false
        }

        binding.cardAbs.setOnClickListener {
            binding.cardAbs.isChecked = !binding.cardAbs.isChecked
            binding.cardFullBody.isChecked = false
        }

        binding.cardLeg.setOnClickListener {
            binding.cardLeg.isChecked = !binding.cardLeg.isChecked
            binding.cardFullBody.isChecked = false
        }

        binding.nextButtonSelectBodyPart.setOnClickListener{
            navController.navigate(
                SelectBodyPartWorkoutFragmentDirections.actionSelectBodyPartWorkoutFragmentToPersonActivityStatusFragment()
            )
        }

    }

}