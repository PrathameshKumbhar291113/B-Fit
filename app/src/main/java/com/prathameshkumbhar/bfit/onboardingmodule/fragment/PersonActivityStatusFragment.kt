package com.prathameshkumbhar.bfit.onboardingmodule.fragment

import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.prathameshkumbhar.bfit.coremodule.BaseFragment
import com.prathameshkumbhar.bfit.databinding.FragmentPersonActivityStatusBinding
import com.prathameshkumbhar.bfit.homemodule.activity.HomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.fragments.start

class PersonActivityStatusFragment : BaseFragment() {
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

        binding.cardSedentary.setOnClickListener {
            setUpCardSedentary()

        }

        binding.cardLightlyActive.setOnClickListener {
            setupCardLightlyActive()
        }

        binding.cardModeratelyActive.setOnClickListener {
            setUpCardModeratelyActive()

        }
        binding.cardAthletic.setOnClickListener {
            setUpCardAthletic()

        }

        binding.nextButtonSelectActivityStatus.setOnClickListener {

            with(binding){
                if (
                    cardAthletic.isChecked ||
                    cardModeratelyActive.isChecked ||
                    cardLightlyActive.isChecked ||
                    cardSedentary.isChecked
                ){
                    setUpProgressBar()
                    progressChangeListener()
                }else{
                    infoToast("Kindly select one of the above activity.")
                }
            }
        }
    }


    private fun setUpCardSedentary(){
        binding.cardSedentary.isChecked = !binding.cardSedentary.isChecked

        binding.cardSedentary.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

        binding.cardSedentary.strokeWidth = 8
        binding.cardLightlyActive.strokeWidth = 0
        binding.cardModeratelyActive.strokeWidth = 0
        binding.cardAthletic.strokeWidth = 0

        binding.cardSedentarySubText.visibility = View.VISIBLE
        binding.cardLightlyActiveSubText.visibility = View.GONE
        binding.cardModeratelyActiveSubText.visibility = View.GONE
        binding.cardAthleticSubText.visibility = View.GONE

        if (!binding.cardSedentary.isChecked){
            binding.cardSedentarySubText.visibility = View.GONE
            binding.cardSedentary.strokeWidth = 0

        }

        binding.cardLightlyActive.isChecked = false
        binding.cardModeratelyActive.isChecked = false
        binding.cardAthletic.isChecked = false
    }


    private fun setupCardLightlyActive(){
        binding.cardLightlyActive.isChecked = !binding.cardLightlyActive.isChecked

        binding.cardLightlyActive.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

        binding.cardSedentary.strokeWidth = 0
        binding.cardLightlyActive.strokeWidth = 8
        binding.cardModeratelyActive.strokeWidth = 0
        binding.cardAthletic.strokeWidth = 0


        binding.cardSedentarySubText.visibility = View.GONE
        binding.cardLightlyActiveSubText.visibility = View.VISIBLE
        binding.cardModeratelyActiveSubText.visibility = View.GONE
        binding.cardAthleticSubText.visibility = View.GONE

        if (!binding.cardLightlyActive.isChecked){
            binding.cardLightlyActiveSubText.visibility = View.GONE
            binding.cardLightlyActive.strokeWidth = 0
        }

        binding.cardSedentary.isChecked = false
        binding.cardModeratelyActive.isChecked = false
        binding.cardAthletic.isChecked = false
    }


    private fun setUpCardModeratelyActive(){

        binding.cardModeratelyActive.isChecked = !binding.cardModeratelyActive.isChecked

        binding.cardModeratelyActive.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

        binding.cardSedentary.strokeWidth = 0
        binding.cardLightlyActive.strokeWidth = 0
        binding.cardModeratelyActive.strokeWidth = 8
        binding.cardAthletic.strokeWidth = 0

        binding.cardSedentarySubText.visibility = View.GONE
        binding.cardLightlyActiveSubText.visibility = View.GONE
        binding.cardModeratelyActiveSubText.visibility = View.VISIBLE
        binding.cardAthleticSubText.visibility = View.GONE

        if (!binding.cardModeratelyActive.isChecked){
            binding.cardModeratelyActiveSubText.visibility = View.GONE
            binding.cardModeratelyActive.strokeWidth = 0
        }

        binding.cardLightlyActive.isChecked = false
        binding.cardSedentary.isChecked = false
        binding.cardAthletic.isChecked = false
    }


    private fun setUpCardAthletic(){
        binding.cardAthletic.isChecked = !binding.cardAthletic.isChecked

        binding.cardAthletic.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#14213D")))

        binding.cardSedentary.strokeWidth = 0
        binding.cardLightlyActive.strokeWidth = 0
        binding.cardModeratelyActive.strokeWidth = 0
        binding.cardAthletic.strokeWidth = 8

        binding.cardSedentarySubText.visibility = View.GONE
        binding.cardLightlyActiveSubText.visibility = View.GONE
        binding.cardModeratelyActiveSubText.visibility = View.GONE
        binding.cardAthleticSubText.visibility = View.VISIBLE

        if (!binding.cardAthletic.isChecked){
            binding.cardAthleticSubText.visibility = View.GONE
            binding.cardAthletic.strokeWidth = 0
        }

        binding.cardLightlyActive.isChecked = false
        binding.cardModeratelyActive.isChecked = false
        binding.cardSedentary.isChecked = false
    }


    private fun setUpProgressBar(){

        binding.scrollViewPersonActivityStatusFrag.visibility = View.GONE
        binding.personActivityTitleTv.visibility = View.GONE
        binding.nextButtonSelectActivityStatus.visibility = View.GONE
        binding.progressCircular.visibility = View.VISIBLE
        binding.progressTextBox.visibility = View.VISIBLE
        binding.progressDetails.visibility = View.VISIBLE
        binding.progressTitle.visibility = View.VISIBLE

        binding.progressCircular.apply {

            progress = 0f
            progressMax = 100f
            progressBarWidth = 35f
            backgroundProgressBarWidth = 35f
            progressBarColorEnd =Color.rgb(77, 126, 221)
            progressBarColorStart = Color.rgb(20, 33, 61)
            backgroundProgressBarColor = Color.rgb(248, 247, 255)
            progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM
            roundBorder = true
            setProgressWithAnimation(0f,2000)
            setProgressWithAnimation(20f,4000)
            setProgressWithAnimation(40f,8000)
            setProgressWithAnimation(60f,10000)
            setProgressWithAnimation(80f,12000)
            setProgressWithAnimation(100f,15000)

        }
    }


    private fun progressChangeListener(){
        val sharePrefOnboarded : SharedPreferences = context!!.getSharedPreferences("onBoardCheck", Context.MODE_PRIVATE)


        binding.progressCircular.onProgressChangeListener = { progress ->

            binding.progressTextBox.text = "${progress.toInt()}%"

            when(progress.toInt()){

                0  -> {
                    binding.progressDetails.text = "Initializing Plan."
                }
                20-> {
                    binding.progressDetails.text = "Implementing Diet."
                }
                50 ->{
                    binding.progressDetails.text = "Half Way Done."
                }
                70 -> {
                    binding.progressDetails.text = "Almost there."
                }
                100-> {
                    binding.progressDetails.text = "PLan Generated Successfully !"
                    lifecycleScope.launch {
                        delay(300)
                        start<HomeActivity>(){
                            var editor: SharedPreferences.Editor = sharePrefOnboarded.edit()
                            editor.putBoolean("isOnboardComplete",true)
                            editor.apply()
                            activity?.finish()
                        }
                    }
                }
            }

        }
    }

}