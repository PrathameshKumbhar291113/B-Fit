package com.prathameshkumbhar.bfit.onboardingmodule.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prathameshkumbhar.bfit.databinding.FragmentPersonActivityStatusBinding
import com.prathameshkumbhar.bfit.mainmodule.HomeActivity
import splitties.fragments.start

class PersonActivityStatusFragment : Fragment() {
    private var _binding : FragmentPersonActivityStatusBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPersonActivityStatusBinding.inflate(inflater, container, false)


        val sharePrefGender: SharedPreferences = context!!.getSharedPreferences("genderCheck", Context.MODE_PRIVATE)
        var checkGender = sharePrefGender.getBoolean("isMaleChecked",false)

        if (checkGender){
            binding.cardSedentaryEmoteTv.text ="\uD83D\uDC68\u200D\uD83D\uDCBB"
            binding.cardLightlyActiveEmoteTv.text ="\uD83D\uDEB6"
            binding.cardModeratelyActiveEmoteTv.text ="\uD83C\uDFC3"
            binding.cardAthleticEmoteTv.text ="⛹️"
        }else{
            binding.cardSedentaryEmoteTv.text ="\uD83D\uDC69\u200D\uD83D\uDCBB"
            binding.cardLightlyActiveEmoteTv.text ="\uD83D\uDEB6\u200D♀️"
            binding.cardModeratelyActiveEmoteTv.text ="\uD83C\uDFC3\u200D♀️"
            binding.cardAthleticEmoteTv.text ="⛹️\u200D♀️"
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharePrefOnboarded : SharedPreferences = context!!.getSharedPreferences("onBoardCheck", Context.MODE_PRIVATE)


        binding.cardSedentary.setOnClickListener {
            binding.cardSedentary.isChecked = !binding.cardSedentary.isChecked

            binding.cardSedentarySubText.visibility = View.VISIBLE
            binding.cardLightlyActiveSubText.visibility = View.GONE
            binding.cardModeratelyActiveSubText.visibility = View.GONE
            binding.cardAthleticSubText.visibility = View.GONE

            if (!binding.cardSedentary.isChecked){
                binding.cardSedentarySubText.visibility = View.GONE
            }

            binding.cardLightlyActive.isChecked = false
            binding.cardModeratelyActive.isChecked = false
            binding.cardAthletic.isChecked = false
        }
        binding.cardLightlyActive.setOnClickListener {
            binding.cardLightlyActive.isChecked = !binding.cardLightlyActive.isChecked


            binding.cardSedentarySubText.visibility = View.GONE
            binding.cardLightlyActiveSubText.visibility = View.VISIBLE
            binding.cardModeratelyActiveSubText.visibility = View.GONE
            binding.cardAthleticSubText.visibility = View.GONE

            if (!binding.cardLightlyActive.isChecked){
                binding.cardLightlyActiveSubText.visibility = View.GONE
            }

            binding.cardSedentary.isChecked = false
            binding.cardModeratelyActive.isChecked = false
            binding.cardAthletic.isChecked = false
        }
        binding.cardModeratelyActive.setOnClickListener {
            binding.cardModeratelyActive.isChecked = !binding.cardModeratelyActive.isChecked

            binding.cardSedentarySubText.visibility = View.GONE
            binding.cardLightlyActiveSubText.visibility = View.GONE
            binding.cardModeratelyActiveSubText.visibility = View.VISIBLE
            binding.cardAthleticSubText.visibility = View.GONE

            if (!binding.cardModeratelyActive.isChecked){
                binding.cardModeratelyActiveSubText.visibility = View.GONE
            }

            binding.cardLightlyActive.isChecked = false
            binding.cardSedentary.isChecked = false
            binding.cardAthletic.isChecked = false
        }
        binding.cardAthletic.setOnClickListener {
            binding.cardAthletic.isChecked = !binding.cardAthletic.isChecked

            binding.cardSedentarySubText.visibility = View.GONE
            binding.cardLightlyActiveSubText.visibility = View.GONE
            binding.cardModeratelyActiveSubText.visibility = View.GONE
            binding.cardAthleticSubText.visibility = View.VISIBLE

            if (!binding.cardAthletic.isChecked){
                binding.cardAthleticSubText.visibility = View.GONE
            }

            binding.cardLightlyActive.isChecked = false
            binding.cardModeratelyActive.isChecked = false
            binding.cardSedentary.isChecked = false
        }

        binding.nextButtonSelectActivityStatus.setOnClickListener {
            start<HomeActivity>(){

                var editor: SharedPreferences.Editor = sharePrefOnboarded.edit()
                editor.putBoolean("isOnboardComplete",true)
                editor.apply()

                activity?.finish()
            }
        }
    }
}