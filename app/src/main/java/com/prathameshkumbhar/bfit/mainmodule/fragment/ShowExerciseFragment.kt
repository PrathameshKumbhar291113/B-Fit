package com.prathameshkumbhar.bfit.mainmodule.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prathameshkumbhar.bfit.databinding.FragmentShowExerciseBinding

class ShowExerciseFragment : Fragment() {
    private var _binding : FragmentShowExerciseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharePrefGender: SharedPreferences = requireContext().getSharedPreferences("genderCheck", Context.MODE_PRIVATE)
        var checkGenderMale = sharePrefGender.getBoolean("isMaleChecked", true)
        if (checkGenderMale){
            binding.textView2.text = "MALE EXERCISES"
        }else{
            binding.textView2.text = "FEMALE EXERCISES"
        }
    }
}