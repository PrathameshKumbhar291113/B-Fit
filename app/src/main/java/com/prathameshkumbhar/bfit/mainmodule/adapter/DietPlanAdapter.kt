package com.prathameshkumbhar.bfit.mainmodule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.prathameshkumbhar.bfit.databinding.ItemDietPlanDetailBinding
import com.prathameshkumbhar.bfit.mainmodule.data.DietPlanDetails

class DietPlanAdapter(
    private val context: Context,
    private val dietPlanList: List<DietPlanDetails>
) : RecyclerView.Adapter<DietPlanAdapter.DietPlanViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): DietPlanViewHolder {
        return DietPlanViewHolder(
            ItemDietPlanDetailBinding
                .inflate(LayoutInflater.from(parent.context), parent,false)
        )
    }

    override fun onBindViewHolder(
        holder: DietPlanViewHolder, position: Int
    ) = holder.bind(dietPlanList[position])

    override fun getItemCount(): Int = dietPlanList.size

    inner class DietPlanViewHolder(
        val binding: ItemDietPlanDetailBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(dietPlanDetails : DietPlanDetails){
            binding.foodImg.load(dietPlanDetails.dietImg)
            binding.timeTv.text = dietPlanDetails.timeTitle.toString()
            binding.descTv.text = dietPlanDetails.dietDesc.toString()
        }
    }

}