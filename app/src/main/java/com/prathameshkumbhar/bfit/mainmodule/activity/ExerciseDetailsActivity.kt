package com.prathameshkumbhar.bfit.mainmodule.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.prathameshkumbhar.bfit.databinding.ActivityExerciseDetailsBinding
import com.prathameshkumbhar.bfit.mainmodule.adapter.ExerciseStepsDetailAdapter
import com.prathameshkumbhar.bfit.mainmodule.data.EXERCISE_SHOWCASE_LIST
import com.prathameshkumbhar.bfit.mainmodule.data.ExerciseDetails

class ExerciseDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseDetailsBinding
    private lateinit var exerciseStepsDetailAdapter : ExerciseStepsDetailAdapter

    private val args : ExerciseDetailsActivityArgs by navArgs()

    private var idReceived: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExerciseDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        args.let {
            idReceived = it.exerciseShowcase.id
        }
        setUpRecyclerView()
    }

    private fun setUpRecyclerView(){
        binding.activityExerciseDetailsToolBar.title = args.exerciseShowcase.exerciseTitle.toString()

            var stepsList = EXERCISE_SHOWCASE_LIST.find { exerciseShowCase ->
                exerciseShowCase.id == idReceived
            }!!.exerciseStepsList

            exerciseStepsDetailAdapter = ExerciseStepsDetailAdapter(
                this,
                this::onItemClick,
                stepsList
            )
        binding.stepsDetailRv.adapter = exerciseStepsDetailAdapter
    }

    private fun onItemClick(exerciseDetails: ExerciseDetails) {
        //DAILOG BOX WILL APPEAR HERE
    }
}