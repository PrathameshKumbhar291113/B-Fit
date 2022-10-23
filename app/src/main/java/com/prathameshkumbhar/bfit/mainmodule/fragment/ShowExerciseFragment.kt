package com.prathameshkumbhar.bfit.mainmodule.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.prathameshkumbhar.bfit.databinding.FragmentShowExerciseBinding
import com.prathameshkumbhar.bfit.mainmodule.adapter.ExerciseAdapter
import com.prathameshkumbhar.bfit.mainmodule.data.EXERCISE_SHOWCASE_LIST
import com.prathameshkumbhar.bfit.mainmodule.data.ExerciseShowCase


class ShowExerciseFragment : Fragment() {
    private lateinit var exerciseAdapter: ExerciseAdapter
    private var _binding : FragmentShowExerciseBinding? = null
    private val binding get() = _binding!!
    private val navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowExerciseBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {

            exerciseAdapter = ExerciseAdapter(
                context!!,
                this::onItemClick,
                EXERCISE_SHOWCASE_LIST
            )
        binding.exerciseShowRecyclerView.adapter = exerciseAdapter

    }

    private fun onItemClick(exerciseShowCase: ExerciseShowCase) {
        navController.navigate(ShowExerciseFragmentDirections.actionShowExerciseFragmentToExerciseDetailsActivity(exerciseShowCase))
    }
}