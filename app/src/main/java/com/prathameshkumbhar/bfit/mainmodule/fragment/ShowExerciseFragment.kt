package com.prathameshkumbhar.bfit.mainmodule.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prathameshkumbhar.bfit.R
import com.prathameshkumbhar.bfit.databinding.FragmentShowExerciseBinding
import com.prathameshkumbhar.bfit.mainmodule.adapter.ExerciseAdapter
import com.prathameshkumbhar.bfit.mainmodule.data.EXERCISE_SHOWCASE_LIST
import com.prathameshkumbhar.bfit.mainmodule.data.ExerciseShowCase


class ShowExerciseFragment : Fragment() {
    private lateinit var exerciseAdapter: ExerciseAdapter
    private var _binding : FragmentShowExerciseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowExerciseBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharePrefGender: SharedPreferences = requireContext().getSharedPreferences("genderCheck", Context.MODE_PRIVATE)
        var checkGenderMale = sharePrefGender.getBoolean("isMaleChecked", true)
        if (checkGenderMale){
//            binding.textView2.text = "MALE EXERCISES"
        }else{
//            binding.textView2.text = "FEMALE EXERCISES"
        }
    }

    private fun setupRecyclerView() {

            exerciseAdapter = ExerciseAdapter(
                this::onItemClick,
                EXERCISE_SHOWCASE_LIST
            )
        binding.exerciseShowRecyclerView.adapter = exerciseAdapter

    }

    private fun onItemClick(exerciseShowCase: ExerciseShowCase) {
        // No need to create fragment manually use factory method
        val fragment = ExerciseStepsDetailShowCaseFragment.newInstance(exerciseShowCase.id)
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.homeActivity_fragmentContainer,fragment)
        fragmentTransaction.commit()
    }

}

// Use this same param key across both the fragments
const val PARAM_ID: String = "param_id"