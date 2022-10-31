package com.prathameshkumbhar.bfit.onboardingmodule.fragment

import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.droidman.ktoasty.KToasty
import com.prathameshkumbhar.bfit.databinding.FragmentBodyTypeGoalBinding

class BodyTypeGoalFragment : Fragment() {

    private var _binding : FragmentBodyTypeGoalBinding? = null
    private val binding get() = _binding!!

    private val  navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBodyTypeGoalBinding.inflate(inflater, container, false)

//        val sharePrefGender: SharedPreferences = context!!.getSharedPreferences("genderCheck", Context.MODE_PRIVATE)
//        var checkGender = sharePrefGender.getBoolean("isMaleChecked",false)
//        if (checkGender){
//            binding.getTonedTv.text = "Keep Fit"
//        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardLooseWeight.setOnClickListener{

            binding.cardLooseWeight.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardLooseWeight.isChecked = true
            if (binding.cardLooseWeight.isChecked){
                binding.cardLooseWeight.strokeWidth = 8
            }

            binding.cardBuildMuscle.isChecked = false
            binding.cardBalance.isChecked = false

            binding.cardBuildMuscle.strokeWidth = 0
            binding.cardBalance.strokeWidth = 0

        }

        binding.cardBuildMuscle.setOnClickListener{

            binding.cardBuildMuscle.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardBuildMuscle.isChecked = true
            if (binding.cardBuildMuscle.isChecked){
                binding.cardBuildMuscle.strokeWidth = 8
            }

            binding.cardLooseWeight.isChecked = false
            binding.cardBalance.isChecked = false

            binding.cardLooseWeight.strokeWidth = 0
            binding.cardBalance.strokeWidth = 0

        }

        binding.cardBalance.setOnClickListener{

            binding.cardBalance.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

            binding.cardBalance.isChecked = true
            if (binding.cardBalance.isChecked){
                binding.cardBalance.strokeWidth = 8
            }

            binding.cardLooseWeight.isChecked = false
            binding.cardBuildMuscle.isChecked = false


            binding.cardLooseWeight.strokeWidth = 0
            binding.cardBuildMuscle.strokeWidth = 0
        }

        binding.nextButtonSelectBodyGoal.setOnClickListener {

            if (binding.cardLooseWeight.isChecked){

                isLooseWtCardCheck()

                navController.navigate(
                    BodyTypeGoalFragmentDirections.actionBodyTypeGoalFragmentToDietTypeSelectFragment()
                )
            }else if (binding.cardBuildMuscle.isChecked){

                isBuildMuscleCardCheck()

                navController.navigate(
                    BodyTypeGoalFragmentDirections.actionBodyTypeGoalFragmentToDietTypeSelectFragment()
                )
            } else if (binding.cardBalance.isChecked){

                isBalanceCardCheck()

                navController.navigate(
                    BodyTypeGoalFragmentDirections.actionBodyTypeGoalFragmentToDietTypeSelectFragment()
                )
            }else{
                KToasty.info(requireContext() ,"Kindly select the goal to get the plan!").show()

            }

        }
    }

    private fun isLooseWtCardCheck(){
        val sharePrefLooseWeight : SharedPreferences = context!!.getSharedPreferences(
            "cardLooseWeightChecked",
            Context.MODE_PRIVATE
        )
        var looseWeightEditor : SharedPreferences.Editor = sharePrefLooseWeight.edit()
        looseWeightEditor.putBoolean("isLooseWeightCardCheck", true)
        looseWeightEditor.apply()


        val sharePrefBuildMuscle : SharedPreferences = context!!.getSharedPreferences(
            "cardBuildMuscleChecked",
            Context.MODE_PRIVATE
        )
        var buildMuscleEditor : SharedPreferences.Editor = sharePrefBuildMuscle.edit()
        buildMuscleEditor.putBoolean("isBuildMuscleCardCheck", false)
        buildMuscleEditor.apply()


        val sharePrefBalance : SharedPreferences = context!!.getSharedPreferences(
            "cardBalanceChecked",
            Context.MODE_PRIVATE
        )
        var balanceEditor : SharedPreferences.Editor = sharePrefBalance.edit()
        balanceEditor.putBoolean("isBalanceCardCheck", false)
        balanceEditor.apply()

    }

    private fun isBuildMuscleCardCheck(){
        val sharePrefLooseWeight : SharedPreferences = context!!.getSharedPreferences(
            "cardLooseWeightChecked",
            Context.MODE_PRIVATE
        )
        var looseWeightEditor : SharedPreferences.Editor = sharePrefLooseWeight.edit()
        looseWeightEditor.putBoolean("isLooseWeightCardCheck", false)
        looseWeightEditor.apply()


        val sharePrefBuildMuscle : SharedPreferences = context!!.getSharedPreferences(
            "cardBuildMuscleChecked",
            Context.MODE_PRIVATE
        )
        var buildMuscleEditor : SharedPreferences.Editor = sharePrefBuildMuscle.edit()
        buildMuscleEditor.putBoolean("isBuildMuscleCardCheck", true)
        buildMuscleEditor.apply()


        val sharePrefBalance : SharedPreferences = context!!.getSharedPreferences(
            "cardBalanceChecked",
            Context.MODE_PRIVATE
        )
        var balanceEditor : SharedPreferences.Editor = sharePrefBalance.edit()
        balanceEditor.putBoolean("isBalanceCardCheck", false)
        balanceEditor.apply()

    }

    private fun isBalanceCardCheck(){
        val sharePrefLooseWeight : SharedPreferences = context!!.getSharedPreferences(
            "cardLooseWeightChecked",
            Context.MODE_PRIVATE
        )
        var looseWeightEditor : SharedPreferences.Editor = sharePrefLooseWeight.edit()
        looseWeightEditor.putBoolean("isLooseWeightCardCheck", false)
        looseWeightEditor.apply()


        val sharePrefBuildMuscle : SharedPreferences = context!!.getSharedPreferences(
            "cardBuildMuscleChecked",
            Context.MODE_PRIVATE
        )
        var buildMuscleEditor : SharedPreferences.Editor = sharePrefBuildMuscle.edit()
        buildMuscleEditor.putBoolean("isBuildMuscleCardCheck", false)
        buildMuscleEditor.apply()


        val sharePrefBalance : SharedPreferences = context!!.getSharedPreferences(
            "cardBalanceChecked",
            Context.MODE_PRIVATE
        )
        var balanceEditor : SharedPreferences.Editor = sharePrefBalance.edit()
        balanceEditor.putBoolean("isBalanceCardCheck", true)
        balanceEditor.apply()

    }


}