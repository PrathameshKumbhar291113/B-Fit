package com.prathameshkumbhar.bfit.mainmodule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.prathameshkumbhar.bfit.databinding.ItemShowExerciseStepsDetailBinding
import com.prathameshkumbhar.bfit.mainmodule.data.ExerciseDetails

class ExerciseStepsDetailAdapter(
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
            binding.exerciseStepDetailImg.load(exerciseDetails.exerciseImageGif)
            binding.exerciseStepDetailImg.setOnClickListener {
                onItemClick(exerciseDetails)
            }
            binding.exerciseStepTitleTv.text = exerciseDetails.exerciseStepName.toString()
            binding.exerciseStepRepetitionTv.text = exerciseDetails.exerciseRepetition.toString()

        }
    }
}