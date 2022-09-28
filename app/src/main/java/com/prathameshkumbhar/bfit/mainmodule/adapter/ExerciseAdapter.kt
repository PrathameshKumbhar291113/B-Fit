package com.prathameshkumbhar.bfit.mainmodule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.prathameshkumbhar.bfit.databinding.ItemShowExerciseBinding
import com.prathameshkumbhar.bfit.mainmodule.data.ExerciseShowCase

class ExerciseAdapter (
    private val exerciseShowCaseList: List<ExerciseShowCase>
    )
    : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(
            ItemShowExerciseBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: ExerciseViewHolder, position: Int
    ) = holder.bind(exerciseShowCaseList[position])

    override fun getItemCount(): Int  = exerciseShowCaseList.size

    inner class ExerciseViewHolder(
        val binding: ItemShowExerciseBinding
    ) : RecyclerView.ViewHolder(binding.root){
        //Inside the inner class
        fun bind(result: ExerciseShowCase){
            binding.exerciseImage.load(result.exerciseImageUrl)

            binding.exerciseTitleTv.text = result.exerciseTitle.toString()
            binding.exerciseRatingTv.text = result.exerciseRating.toString()

        }
    }
}