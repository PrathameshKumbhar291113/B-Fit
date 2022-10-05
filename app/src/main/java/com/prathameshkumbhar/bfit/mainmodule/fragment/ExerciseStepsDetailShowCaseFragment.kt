package com.prathameshkumbhar.bfit.mainmodule.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prathameshkumbhar.bfit.R
import com.prathameshkumbhar.bfit.databinding.FragmentExerciseStepsDetailShowCaseBinding
import com.prathameshkumbhar.bfit.mainmodule.adapter.ExerciseStepsDetailAdapter
import com.prathameshkumbhar.bfit.mainmodule.data.EXERCISE_SHOWCASE_LIST
import com.prathameshkumbhar.bfit.mainmodule.data.ExerciseDetails

/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseStepsDetailShowCaseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseStepsDetailShowCaseFragment : Fragment() {
    private var _binding : FragmentExerciseStepsDetailShowCaseBinding? = null
    private val binding get() = _binding!!

    private lateinit var exerciseStepsDetailAdapter : ExerciseStepsDetailAdapter


    private var idReceived: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idReceived = it.getInt(PARAM_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentExerciseStepsDetailShowCaseBinding.inflate(inflater,container,false)

        setupRecyclerView()


        return  binding.root

    }

    private fun setupRecyclerView() {

        var stepsList = EXERCISE_SHOWCASE_LIST.find { exerciseShowCase ->
            // Here we compare the id we recieved from previoseu fragement with all the exerciseShowcase in the list
            // and then we get the one we need
            exerciseShowCase.id == idReceived
        }!!.exerciseStepsList
//        Timber.i(stepsList.toString())
        // You recieve exerciseShowcase first which has reference to exerciseSTepslist

//        binding.stepsDetailRv.adapter = exerciseStepsDetailAdapter
//        // Then you can use the list to populate the recyclerview
//        binding.stepsDetailRv.adapter = ExerciseStepsDetailAdapter(
//            this::onItemClick,
//            stepsList
//        )

            exerciseStepsDetailAdapter = ExerciseStepsDetailAdapter(
                this::onItemClick,
                stepsList
            )
        binding.stepsDetailRv.adapter = exerciseStepsDetailAdapter
    }

    private fun onItemClick(exerciseDetails: ExerciseDetails) {
        // No need to create fragment manually use factory method
        val fragment = newInstance(exerciseDetails.id)
        replaceFragment(fragment)
    }


    companion object {
        // Read this pre generated comment
        // This function is generated so that you don't have to do fragment transaction yourselvses
        // So to create this fragment we can use this function
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment ExerciseStepsDetailShowCaseFragment.
         */
        @JvmStatic
        fun newInstance(param1: Int) = ExerciseStepsDetailShowCaseFragment().apply {
                arguments = Bundle().apply {
                    putInt(PARAM_ID, param1)
                }
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.homeActivity_fragmentContainer,fragment)
        fragmentTransaction.commit()
    }
}