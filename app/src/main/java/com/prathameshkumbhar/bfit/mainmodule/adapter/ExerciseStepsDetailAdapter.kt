package com.prathameshkumbhar.bfit.mainmodule.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prathameshkumbhar.bfit.databinding.ItemShowExerciseStepsDetailBinding
import com.prathameshkumbhar.bfit.mainmodule.data.ExerciseDetails

class ExerciseStepsDetailAdapter(
    private val context: Context,
    private val onItemClick: (ExerciseDetails) -> Unit,
    private val exerciseStepsDetailList: List<ExerciseDetails>
): RecyclerView.Adapter<ExerciseStepsDetailAdapter.ExerciseStepsDetailViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExerciseStepsDetailViewHolder {
        return ExerciseStepsDetailViewHolder(
            ItemShowExerciseStepsDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(
        holder: ExerciseStepsDetailViewHolder, position: Int
    ) = holder.bind(exerciseStepsDetailList[position])

    override fun getItemCount(): Int = exerciseStepsDetailList.size

    inner class ExerciseStepsDetailViewHolder(
        val binding: ItemShowExerciseStepsDetailBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(exerciseDetails: ExerciseDetails){

            Glide.with(context).
            load(exerciseDetails.exerciseImageGif).
            into(binding.exerciseStepDetailImg)

            binding.exerciseStepDetailImg.setOnClickListener {
                onItemClick(exerciseDetails)
            }

            binding.exerciseStepTitleTv.text = exerciseDetails.exerciseStepName.toString()

           repetitionOfExerciseAsPerDifficulty(exerciseDetails)
        }

        private fun repetitionOfExerciseAsPerDifficulty(exerciseDetails: ExerciseDetails){

            val sharePrefBeginner : SharedPreferences = context.getSharedPreferences("cardBeginner", Context.MODE_PRIVATE)
            var isBeginnerCardSelected = sharePrefBeginner.getBoolean("isBeginnerCheck", false)

            if (isBeginnerCardSelected){
                binding.exerciseStepRepetitionTv.text = exerciseDetails.exerciseRepetitionBeginner.toString()
            }


            val sharePrefIntermediate : SharedPreferences = context.getSharedPreferences("cardIntermediate", Context.MODE_PRIVATE)
            var isIntermediateCardSelected = sharePrefIntermediate.getBoolean("isIntermediateCardCheck", false)

            if (isIntermediateCardSelected){
                binding.exerciseStepRepetitionTv.text = exerciseDetails.exerciseRepetitionIntermediate.toString()
            }


            val sharePrefAdvance : SharedPreferences = context.getSharedPreferences("cardAdvance", Context.MODE_PRIVATE)
            var isAdvanceCardSelected = sharePrefAdvance.getBoolean("isAdvanceCardCheck", false)

            if (isAdvanceCardSelected){
                binding.exerciseStepRepetitionTv.text = exerciseDetails.exerciseRepetitionAdvance.toString()
            }
        }
    }
}