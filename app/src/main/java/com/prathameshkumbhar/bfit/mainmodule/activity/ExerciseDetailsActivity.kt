package com.prathameshkumbhar.bfit.mainmodule.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.prathameshkumbhar.bfit.databinding.ActivityExerciseDetailsBinding
import com.prathameshkumbhar.bfit.databinding.DialogExerciseDetailsShowCardBinding
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

        binding.activityExerciseDetailsToolBar.setNavigationOnClickListener {
            onBackPressed()
        }
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
        val dialogBinding: DialogExerciseDetailsShowCardBinding = DialogExerciseDetailsShowCardBinding.inflate(layoutInflater)

        val exerciseStepDetailCustomDialog = Dialog(this)

        exerciseStepDetailCustomDialog.apply {
            setContentView(dialogBinding.root)
            setCancelable(false)

            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            Glide.with(baseContext).load(exerciseDetails.exerciseImageGif).into(dialogBinding.exerciseStepImageCard)
            dialogBinding.exerciseStepNameCard.text = exerciseDetails.exerciseStepName.toString()
            dialogBinding.exerciseStepDetailsTextCard.text = exerciseDetails.exerciseStepsDetailTips.toString()

        }.show()

        dialogBinding.exerciseStepsCloseCard.setOnClickListener {
            exerciseStepDetailCustomDialog.dismiss()
        }
    }
}