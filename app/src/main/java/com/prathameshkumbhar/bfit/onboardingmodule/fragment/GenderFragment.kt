package com.prathameshkumbhar.bfit.onboardingmodule.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
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

        binding.maleImg.load("https://i.imgur.com/5epNbBi.png"){
            crossfade(true)
        }

        binding.femaleImg.load("https://i.imgur.com/5epNbBi.png"){
            crossfade(true)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.maleButton.setOnClickListener{
            val sharePrefGender: SharedPreferences = context!!.getSharedPreferences("genderMale", Context.MODE_PRIVATE)
            var editor: SharedPreferences.Editor = sharePrefGender.edit()
            editor.putString("maleBtn","selectedMale")
            editor.apply()
            navController.navigate(
                GenderFragmentDirections.actionGenderFragmentToMaleHeightWeightFragment()
            )
        }

        binding.femaleButton.setOnClickListener{
            val sharePrefGender: SharedPreferences = context!!.getSharedPreferences("genderFemale", Context.MODE_PRIVATE)
            var editor: SharedPreferences.Editor = sharePrefGender.edit()
            editor.putString("femaleBtn","selectedFemale")
            editor.apply()
            navController.navigate(
                GenderFragmentDirections.actionGenderFragmentToFemaleHeightWeightFragment()
            )
        }
    }
}