package com.prathameshkumbhar.bfit.mainmodule.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prathameshkumbhar.bfit.databinding.ItemShowExerciseBinding
import com.prathameshkumbhar.bfit.mainmodule.data.ExerciseShowCase

class ExerciseAdapter (private val exerciseList: ArrayList<ExerciseShowCase> )
    : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>(){

    inner class ExerciseViewHolder(
        val binding: ItemShowExerciseBinding
    ) : RecyclerView.ViewHolder(binding.root){

        //Inside the inner class

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}