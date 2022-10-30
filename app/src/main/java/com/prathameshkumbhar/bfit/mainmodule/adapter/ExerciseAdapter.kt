package com.prathameshkumbhar.bfit.mainmodule.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.prathameshkumbhar.bfit.databinding.ItemShowExerciseBinding
import com.prathameshkumbhar.bfit.mainmodule.data.ExerciseShowCase

class ExerciseAdapter (
    private val context: Context,
    private val onItemClick: (ExerciseShowCase) -> Unit,
    private val exerciseShowCaseList: List<ExerciseShowCase>
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ExerciseViewHolder {
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
        fun bind(exerciseShowCase: ExerciseShowCase){

            genderImageLoading(exerciseShowCase)

            binding.exerciseShowItemCard.setOnClickListener {
               onItemClick(exerciseShowCase)
            }

            binding.exerciseTitleTv.text = exerciseShowCase.exerciseTitle.toString()

            difficultyLevelShowcase(exerciseShowCase)

        }

        private fun genderImageLoading(exerciseShowCase: ExerciseShowCase){

            val sharePrefGender: SharedPreferences = context.getSharedPreferences("genderCheck", Context.MODE_PRIVATE)
            var checkGenderMale = sharePrefGender.getBoolean("isMaleChecked", false)
            if (checkGenderMale){
                binding.exerciseImage.load(exerciseShowCase.exerciseImageUrlMale)
            }else{
                binding.exerciseImage.load(exerciseShowCase.exerciseImageUrlFemale)
            }
        }

        private fun difficultyLevelShowcase(exerciseShowCase: ExerciseShowCase){

            val sharePrefBeginner : SharedPreferences = context.getSharedPreferences(
                "cardBeginner",
                Context.MODE_PRIVATE
            )
            var isBeginnerCardSelected = sharePrefBeginner.getBoolean(
                "isBeginnerCardCheck", false
            )

            val sharePrefIntermediate : SharedPreferences = context.getSharedPreferences(
                "cardIntermediate",
                Context.MODE_PRIVATE
            )
            var isIntermediateCardSelected = sharePrefIntermediate.getBoolean(
                "isIntermediateCardCheck", false
            )

            val sharePrefAdvance : SharedPreferences = context.getSharedPreferences(
                "cardAdvance",
                Context.MODE_PRIVATE
            )

            var isAdvanceCardSelected = sharePrefAdvance.getBoolean("isAdvanceCardCheck", false)

            if (isBeginnerCardSelected){
                binding.exerciseRatingTv.text = exerciseShowCase.exerciseRatingBeginner.toString()
            }else if (isIntermediateCardSelected){
                binding.exerciseRatingTv.text = exerciseShowCase.exerciseRatingIntermediate.toString()
            }else if (isAdvanceCardSelected){
                binding.exerciseRatingTv.text = exerciseShowCase.exerciseRatingAdvance.toString()
            }
        }
    }
}