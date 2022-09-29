package com.prathameshkumbhar.bfit.mainmodule.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.prathameshkumbhar.bfit.R
import com.prathameshkumbhar.bfit.mainmodule.data.EXERCISE_SHOWCASE_LIST


/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseStepsDetailShowCaseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseStepsDetailShowCaseFragment : Fragment() {
    // TODO: Rename and change types of parameters

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


        val stepsList = EXERCISE_SHOWCASE_LIST.find { exerciseShowCase ->
            // Here we compare the id we recieved from previoseu fragement with all the exerciseShowcase in the list
            // and then we get the one we need
            exerciseShowCase.id == idReceived
        }!!.exerciseStepsList
        // You recieve exerciseShowcase first which has reference to exerciseSTepslist
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exercise_steps_detail_show_case, container, false)

       val recyclerView = view.findViewById<RecyclerView>(R.id.steps_detail_rv)
        // Then you can use the list to populate the recyclerview
//        recyclerView.adapter = ExerciseStepsDetailAdapter(stepsList)
        return  view

    }

    companion object {
        // Read this pregenerated comment
        // This function is generated so that you don't have to do frament transaction yourselvses
        // So to create this fragment we can use this function
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExerciseStepsDetailShowCaseFragment.
         */
        @JvmStatic
        fun newInstance(param1: Int) =
            ExerciseStepsDetailShowCaseFragment().apply {
                arguments = Bundle().apply {
                    putInt(PARAM_ID, param1)
                }
            }
    }
}