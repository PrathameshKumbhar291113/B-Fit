package com.prathameshkumbhar.bfit.mainmodule.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import com.droidman.ktoasty.showSuccessToast
import com.google.firebase.auth.FirebaseAuth
import com.prathameshkumbhar.bfit.BuildConfig
import com.prathameshkumbhar.bfit.R
import com.prathameshkumbhar.bfit.coremodule.SplashActivity
import com.prathameshkumbhar.bfit.databinding.FragmentProfileBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.fragments.start
import timber.log.Timber


class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        firebaseAuth = FirebaseAuth.getInstance()

        settingUserProfileImg()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val versionCode: String = BuildConfig.VERSION_NAME
        binding.versionNumTv.text = versionCode

        binding.logoutButton.setOnClickListener {

            //Making the all shared preferences value to false again to make it set for the default
            //value
            isLoggedIn()
            isOnBoarded()
            resetLevelOfExercise()
            resetBodyTypeGoal()
            resetDietType()
            resetUserAge()


            requireContext().showSuccessToast("Successfully Logged Out!")
            firebaseAuth.signOut()
            lifecycleScope.launch(){
                delay(1000)
                start<SplashActivity>(){
                    activity?.finish()
                }
            }
        }
    }


    private fun isLoggedIn(){
        //putting value 'false' in the shared preference as the user is route to the splash
        //activity, they will again route to the login fragment as the value now stored is
        //false as if the value stored in the logged in is false then the user will have to
        //log in again

        val sharePrefLogin : SharedPreferences = context!!.getSharedPreferences("login", Context.MODE_PRIVATE)
        var editorlog : SharedPreferences.Editor = sharePrefLogin.edit()
        editorlog.putBoolean("flag",false)
        editorlog.apply()

    }

    private fun isOnBoarded(){
        //Same goes for the on board activity fragment also
        val sharePrefOnboarded : SharedPreferences = context!!.getSharedPreferences("onBoardCheck", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharePrefOnboarded.edit()
        editor.putBoolean("isOnboardComplete",false)
        editor.apply()

    }

    private fun resetLevelOfExercise(){
        //Changing the already selected level of exercise to default false
        val sharePrefBeginner : SharedPreferences = context!!.getSharedPreferences("cardBeginner", Context.MODE_PRIVATE)
        var beginnerEditor : SharedPreferences.Editor = sharePrefBeginner.edit()
        beginnerEditor.putBoolean("isBeginnerCardCheck", false)
        beginnerEditor.apply()


        val sharePrefIntermediate : SharedPreferences = context!!.getSharedPreferences("cardIntermediate", Context.MODE_PRIVATE)
        var intermediateEditor : SharedPreferences.Editor = sharePrefIntermediate.edit()
        intermediateEditor.putBoolean("isIntermediateCardCheck", false)
        intermediateEditor.apply()


        val sharePrefAdvance : SharedPreferences = context!!.getSharedPreferences("cardAdvance", Context.MODE_PRIVATE)
        var advanceEditor : SharedPreferences.Editor = sharePrefAdvance.edit()
        advanceEditor.putBoolean("isAdvanceCardCheck", false)
        advanceEditor.apply()

    }

    private fun resetBodyTypeGoal(){
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
        balanceEditor.putBoolean("isBalanceCardCheck", false)
        balanceEditor.apply()
    }

    private fun resetDietType(){
        val sharePrefVegDiet : SharedPreferences = context!!.getSharedPreferences(
            "cardVegChecked",
            Context.MODE_PRIVATE
        )
        var vegDietEditor : SharedPreferences.Editor = sharePrefVegDiet.edit()
        vegDietEditor.putBoolean("isVegCardCheck", false)
        vegDietEditor.apply()


        val sharePrefCardNonVeg : SharedPreferences = context!!.getSharedPreferences(
            "cardNonVegChecked",
            Context.MODE_PRIVATE
        )
        var nonVegEditor : SharedPreferences.Editor = sharePrefCardNonVeg.edit()
        nonVegEditor.putBoolean("isNonVegCardCheck", false)
        nonVegEditor.apply()


        val sharePrefMixedDiet : SharedPreferences = context!!.getSharedPreferences(
            "cardMixedDietChecked",
            Context.MODE_PRIVATE
        )
        var mixedDietEditor : SharedPreferences.Editor = sharePrefMixedDiet.edit()
        mixedDietEditor.putBoolean("isMixDietCardCheck", false)
        mixedDietEditor.apply()
    }

    private fun resetUserAge(){

        val sharePrefSaveAge : SharedPreferences = context!!.getSharedPreferences("SaveAge",Context.MODE_APPEND)
        var ageEditor : SharedPreferences.Editor = sharePrefSaveAge.edit()
        ageEditor.clear()
        ageEditor.apply()

    }

    private fun settingUserProfileImg(){

        val sharePrefGender: SharedPreferences = context!!.getSharedPreferences("genderCheck", Context.MODE_PRIVATE)
        var checkGenderMale = sharePrefGender.getBoolean("isMaleChecked", false)

        var maleUserImgList = ArrayList<Int>()
        maleUserImgList.add(R.drawable.usermale1)
        maleUserImgList.add(R.drawable.usermale2)

        var femaleUserImgList = ArrayList<Int>()
        femaleUserImgList.add(R.drawable.userfemale1)
        femaleUserImgList.add(R.drawable.userfemale2)

        // we can pass the image as per the age if the age is <= 24 then set image male1 and if
        // age is > 24 then pass male 2 image. Same goes for female.

        val sharePrefSaveAge : SharedPreferences = context!!.getSharedPreferences("SaveAge",Context.MODE_PRIVATE)
        var userAge = sharePrefSaveAge.getInt("age",0)
        Timber.e("$userAge is the age of user")


        if (checkGenderMale){
            binding.userNameShowTv.text = firebaseAuth.currentUser!!.displayName.toString()

            if (userAge <=24){
                binding.userProfileImg.load(maleUserImgList[0])
            }else{
                binding.userProfileImg.load(maleUserImgList[1])
            }

        }else{
            binding.userNameShowTv.text = firebaseAuth.currentUser!!.displayName.toString()

            if (userAge <=24){
                binding.userProfileImg.load(femaleUserImgList[0])
            }else{
                binding.userProfileImg.load(femaleUserImgList[1])
            }

        }
    }
}