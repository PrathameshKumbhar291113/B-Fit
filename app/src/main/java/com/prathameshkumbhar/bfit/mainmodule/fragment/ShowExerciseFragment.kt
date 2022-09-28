package com.prathameshkumbhar.bfit.mainmodule.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prathameshkumbhar.bfit.databinding.FragmentShowExerciseBinding
import com.prathameshkumbhar.bfit.mainmodule.adapter.ExerciseAdapter
import com.prathameshkumbhar.bfit.mainmodule.data.ExerciseDetails
import com.prathameshkumbhar.bfit.mainmodule.data.ExerciseShowCase


class ShowExerciseFragment : Fragment() {
    private lateinit var exerciseAdapter: ExerciseAdapter
    private var _binding : FragmentShowExerciseBinding? = null
    private val binding get() = _binding!!
    private var exerciseShowCaseList = ArrayList<ExerciseShowCase>()

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

        exerciseShowCaseList = ArrayList()
        exerciseAdapter = ExerciseAdapter(exerciseShowCaseList)



        val upperBody = listOf(
            ExerciseDetails(1,"Jumping Jacks", null,"10x2"),
            ExerciseDetails(2,"Touch Toe",null,"10x2")
        )
        val lowerBody = listOf(
            ExerciseDetails(1,"Jumping Jacks", null,"10x2"),
            ExerciseDetails(2,"Touch Toe",null,"10x2")
        )
        val abs = listOf(
            ExerciseDetails(1,"Jumping Jacks", null,"10x2"),
            ExerciseDetails(2,"Touch Toe",null,"10x2")
        )
        val chest = listOf(
            ExerciseDetails(1,"Jumping Jacks", null,"10x2"),
            ExerciseDetails(2,"Touch Toe",null,"10x2")
        )
        val shoulder = listOf(
            ExerciseDetails(1,"Jumping Jacks", null,"10x2"),
            ExerciseDetails(2,"Touch Toe",null,"10x2")
        )
        val back = listOf(
            ExerciseDetails(1,"Jumping Jacks", null,"10x2"),
            ExerciseDetails(2,"Touch Toe",null,"10x2")
        )
        val biceps = listOf(
            ExerciseDetails(1,"Jumping Jacks", null,"10x2"),
            ExerciseDetails(2,"Touch Toe",null,"10x2")
        )
        val triceps = listOf(
            ExerciseDetails(1,"Jumping Jacks", null,"10x2"),
            ExerciseDetails(2,"Touch Toe",null,"10x2")
        )

        exerciseShowCaseList.add(ExerciseShowCase("Upper Body","https://i.imgur.com/fi4BK5A.jpg","Easy",upperBody))
        exerciseShowCaseList.add(ExerciseShowCase("Lower Body","https://i.imgur.com/fi4BK5A.jpg","Easy",lowerBody))
        exerciseShowCaseList.add(ExerciseShowCase("Abs","https://i.imgur.com/fi4BK5A.jpg","Easy",abs))
        exerciseShowCaseList.add(ExerciseShowCase("Chest","https://i.imgur.com/fi4BK5A.jpg","Easy",chest))
        exerciseShowCaseList.add(ExerciseShowCase("Shoulder","https://i.imgur.com/fi4BK5A.jpg","Easy",shoulder))
        exerciseShowCaseList.add(ExerciseShowCase("Back","https://i.imgur.com/fi4BK5A.jpg","Easy",back))
        exerciseShowCaseList.add(ExerciseShowCase("Biceps","https://i.imgur.com/fi4BK5A.jpg","Easy",biceps))
        exerciseShowCaseList.add(ExerciseShowCase("Triceps","https://i.imgur.com/fi4BK5A.jpg","Easy",triceps))


        binding.exerciseShowRecyclerView.adapter = exerciseAdapter
    }
}