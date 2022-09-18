package com.prathameshkumbhar.bfit.onboardingmodule.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.prathameshkumbhar.bfit.databinding.FragmentGenderBinding

class GenderFragment : Fragment() {
    private var _binding: FragmentGenderBinding? = null
    private val binding get() = _binding!!
    private val  navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenderBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharePrefGender: SharedPreferences = context!!.getSharedPreferences("genderCheck", Context.MODE_PRIVATE)
        binding.maleButton.setOnClickListener{

            var editor: SharedPreferences.Editor = sharePrefGender.edit()
            editor.putBoolean("isMaleChecked",true)
            editor.apply()

            navController.navigate(
                GenderFragmentDirections.actionGenderFragmentToBmiCalculatorFragment()
            )
        }

        binding.femaleButton.setOnClickListener{

            var editor: SharedPreferences.Editor = sharePrefGender.edit()
            editor.putBoolean("isMaleChecked",false)
            editor.apply()

            navController.navigate(
                GenderFragmentDirections.actionGenderFragmentToBmiCalculatorFragment()
            )

        }

    }
}