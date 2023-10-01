package com.prathameshkumbhar.bfit.homemodule.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prathameshkumbhar.bfit.databinding.ItemShowExerciseStepsDetailBinding
import com.prathameshkumbhar.bfit.homemodule.data.ExerciseDetails

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

            binding.exerciseStepsDetailItemCard.setOnClickListener {
                onItemClick(exerciseDetails)
            }

            binding.exerciseStepTitleTv.text = exerciseDetails.exerciseStepName.toString()

           repetitionOfExerciseAsPerDifficulty(exerciseDetails)
        }

        private fun repetitionOfExerciseAsPerDifficulty(exerciseDetails: ExerciseDetails){

            val sharePrefBeginner : SharedPreferences = context.getSharedPreferences("cardBeginner", Context.MODE_PRIVATE)
            var isBeginnerCardSelected = sharePrefBeginner.getBoolean("isBeginnerCardCheck", false)

            val sharePrefIntermediate : SharedPreferences = context.getSharedPreferences("cardIntermediate", Context.MODE_PRIVATE)
            var isIntermediateCardSelected = sharePrefIntermediate.getBoolean("isIntermediateCardCheck", false)

            val sharePrefAdvance : SharedPreferences = context.getSharedPreferences("cardAdvance", Context.MODE_PRIVATE)
            var isAdvanceCardSelected = sharePrefAdvance.getBoolean("isAdvanceCardCheck", false)


            if (isBeginnerCardSelected){
                binding.exerciseStepRepetitionTv.text = exerciseDetails.exerciseRepetitionBeginner.toString()
            }else if (isIntermediateCardSelected){
                binding.exerciseStepRepetitionTv.text = exerciseDetails.exerciseRepetitionIntermediate.toString()
            }else if (isAdvanceCardSelected){
                binding.exerciseStepRepetitionTv.text = exerciseDetails.exerciseRepetitionAdvance.toString()
            }
        }
    }
}