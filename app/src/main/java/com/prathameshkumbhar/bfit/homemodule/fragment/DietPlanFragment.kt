package com.prathameshkumbhar.bfit.homemodule.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prathameshkumbhar.bfit.databinding.FragmentDietPlanBinding
import com.prathameshkumbhar.bfit.homemodule.adapter.DietPlanAdapter
import com.prathameshkumbhar.bfit.homemodule.data.DIET_PLAN_BALANCED_MIXED_LIST
import com.prathameshkumbhar.bfit.homemodule.data.DIET_PLAN_BALANCED_NON_VEG_LIST
import com.prathameshkumbhar.bfit.homemodule.data.DIET_PLAN_BALANCED_VEG_LIST
import com.prathameshkumbhar.bfit.homemodule.data.DIET_PLAN_BUILD_MUSCLE_MIXED_LIST
import com.prathameshkumbhar.bfit.homemodule.data.DIET_PLAN_BUILD_MUSCLE_NON_VEG_LIST
import com.prathameshkumbhar.bfit.homemodule.data.DIET_PLAN_BUILD_MUSCLE_VEG_LIST
import com.prathameshkumbhar.bfit.homemodule.data.DIET_PLAN_LOOSE_WT_MIXED_LIST
import com.prathameshkumbhar.bfit.homemodule.data.DIET_PLAN_LOOSE_WT_NON_VEG_LIST
import com.prathameshkumbhar.bfit.homemodule.data.DIET_PLAN_LOOSE_WT_VEG_LIST
import com.prathameshkumbhar.bfit.homemodule.data.DietPlanDetails


class DietPlanFragment : Fragment() {
    private lateinit var dietPlanAdapter: DietPlanAdapter
    private var _binding : FragmentDietPlanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDietPlanBinding.inflate(inflater, container, false)

        setUpRecyclerView()

        return binding.root
    }

    private fun setUpRecyclerView() {

        dietPlanAdapter = DietPlanAdapter(
            context!!,
            dietListSortAsPerDietGoalAndType()
        )

        binding.dietPlanRecyclerView.adapter = dietPlanAdapter

    }

    private fun dietListSortAsPerDietGoalAndType() : List<DietPlanDetails>{
        var finalDietListToPassInAdapter : List<DietPlanDetails> = ArrayList()

        val sharePrefLooseWeight : SharedPreferences = context!!.getSharedPreferences(
            "cardLooseWeightChecked",
            Context.MODE_PRIVATE
        )
        var isLooseWtCardCheck = sharePrefLooseWeight.getBoolean("isLooseWeightCardCheck", false)


        val sharePrefBuildMuscle : SharedPreferences = context!!.getSharedPreferences(
            "cardBuildMuscleChecked",
            Context.MODE_PRIVATE
        )
        var isBuildMuscleCardCheck = sharePrefBuildMuscle.getBoolean("isBuildMuscleCardCheck", false)


        val sharePrefBalance : SharedPreferences = context!!.getSharedPreferences(
            "cardBalanceChecked",
            Context.MODE_PRIVATE
        )
        var isBalanceCardCheck = sharePrefBalance.getBoolean("isBalanceCardCheck", false)


        val sharePrefVegDiet : SharedPreferences = context!!.getSharedPreferences(
            "cardVegChecked",
            Context.MODE_PRIVATE
        )
        var isVegCardCheck = sharePrefVegDiet.getBoolean("isVegCardCheck", false)


        val sharePrefCardNonVeg : SharedPreferences = context!!.getSharedPreferences(
            "cardNonVegChecked",
            Context.MODE_PRIVATE
        )
        var isNonVegCardCheck = sharePrefCardNonVeg.getBoolean("isNonVegCardCheck", false)


        val sharePrefMixedDiet : SharedPreferences = context!!.getSharedPreferences(
            "cardMixedDietChecked",
            Context.MODE_PRIVATE
        )
        var isMixDietCardCheck = sharePrefMixedDiet.getBoolean("isMixDietCardCheck", false)

        if (isLooseWtCardCheck){

            if (isVegCardCheck){

                finalDietListToPassInAdapter = DIET_PLAN_LOOSE_WT_VEG_LIST

                binding.dietPlanIndicatorTv.text = "Loose Weight : Veg"

            }else if (isNonVegCardCheck){

                finalDietListToPassInAdapter = DIET_PLAN_LOOSE_WT_NON_VEG_LIST

                binding.dietPlanIndicatorTv.text = "Loose Weight : Non-Veg"

            }else if (isMixDietCardCheck){
                finalDietListToPassInAdapter = DIET_PLAN_LOOSE_WT_MIXED_LIST

                binding.dietPlanIndicatorTv.text = "Loose Weight : Mix"
            }

        }else if (isBuildMuscleCardCheck){

            if (isVegCardCheck){

                finalDietListToPassInAdapter = DIET_PLAN_BUILD_MUSCLE_VEG_LIST

                binding.dietPlanIndicatorTv.text = "Build Muscle : Veg"

            }else if (isNonVegCardCheck){

                finalDietListToPassInAdapter = DIET_PLAN_BUILD_MUSCLE_NON_VEG_LIST

                binding.dietPlanIndicatorTv.text = "Build Muscle : Non-Veg"

            }else if (isMixDietCardCheck){

                finalDietListToPassInAdapter = DIET_PLAN_BUILD_MUSCLE_MIXED_LIST

                binding.dietPlanIndicatorTv.text = "Build Muscle : Mix"
            }

        }else if (isBalanceCardCheck){

            if (isVegCardCheck){

                finalDietListToPassInAdapter = DIET_PLAN_BALANCED_VEG_LIST

                binding.dietPlanIndicatorTv.text = "Balance Diet : Veg"

            }else if (isNonVegCardCheck){

                finalDietListToPassInAdapter = DIET_PLAN_BALANCED_NON_VEG_LIST


                binding.dietPlanIndicatorTv.text = "Balance Diet : Non-Veg"

            }else if (isMixDietCardCheck){

                finalDietListToPassInAdapter = DIET_PLAN_BALANCED_MIXED_LIST

                binding.dietPlanIndicatorTv.text = "Balance Diet : Mix"
            }

        }

        return finalDietListToPassInAdapter

    }


}