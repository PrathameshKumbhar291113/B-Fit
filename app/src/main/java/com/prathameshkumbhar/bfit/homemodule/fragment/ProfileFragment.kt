package com.prathameshkumbhar.bfit.homemodule.fragment

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.firebase.auth.FirebaseAuth
import com.prathameshkumbhar.bfit.BuildConfig
import com.prathameshkumbhar.bfit.R
import com.prathameshkumbhar.bfit.coremodule.BaseFragment
import com.prathameshkumbhar.bfit.coremodule.SplashActivity
import com.prathameshkumbhar.bfit.databinding.DialogUserRecordBinding
import com.prathameshkumbhar.bfit.databinding.FragmentProfileBinding
import com.prathameshkumbhar.bfit.homemodule.activity.ChangeDietPlanActivity
import com.prathameshkumbhar.bfit.homemodule.activity.ChangeWorkoutPlanActivity
import com.prathameshkumbhar.bfit.homemodule.activity.CreditActivity
import com.prathameshkumbhar.bfit.homemodule.activity.PersonalGuidanceActivity
import com.prathameshkumbhar.bfit.homemodule.activity.PrivacyPolicyActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.fragments.start
import timber.log.Timber


class ProfileFragment : BaseFragment() {
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth : FirebaseAuth
    private val navController by lazy {
        findNavController()
    }

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

        binding.userDetailsCard.setOnClickListener {
            userRecDialogBox()
        }

        binding.contactCard.setOnClickListener {

            successToast("Our team will connect to you within 2 working days, Thank You")


            lifecycleScope.launch {
                delay(2000)
                contactUsSendEmail(
                    context!!,
                    listOf("bfit.company1311@gmail.com"),
                    "Need Help Regarding Issue Discussed Below",
                    "Start writing the query from here:\n")
            }
        }

        binding.communityCard.setOnClickListener {
            shareApplicationWithCommunity()
        }

        //Navigating to the credit activity.
        binding.creditsCard.setOnClickListener {
            start<CreditActivity>()
        }

        //Navigating to the personal diet activity.
        binding.personalDietCard.setOnClickListener {
            start<PersonalGuidanceActivity>()
        }

        //Navigating to Privacy Policy Activity
        binding.privacyPolicyCard.setOnClickListener {
            start<PrivacyPolicyActivity>()
        }

        //Navigating to ChangeDietPlanActivity
        binding.dietChange.setOnClickListener {
            start<ChangeDietPlanActivity>()
        }

        //Navigating to ChangeWorkoutPlanActivity
        binding.workoutChange.setOnClickListener {
            start<ChangeWorkoutPlanActivity>()
        }

        binding.rateUsCard.setOnClickListener {

            try {
                val marketUri: Uri = Uri.parse("market://details?id=" + context!!.packageName)
                val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
                startActivity(marketIntent)
            } catch (e: ActivityNotFoundException) {
                val marketUri: Uri = Uri.parse("https://play.google.com/store/apps/details?id=" + context!!.packageName)
                val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
                startActivity(marketIntent)
            }

        }


        binding.logoutButton.setOnClickListener {

            //Making the all shared preferences value to false again to make it set for the default
            //value
            isLoggedIn()
            isOnBoarded()
            resetLevelOfExercise()
            resetBodyTypeGoal()
            resetDietType()
            resetUserDetails()

            successToast("Successfully Logged Out !")
            firebaseAuth.signOut()
            lifecycleScope.launch(){
                delay(1000)
                start<SplashActivity>(){
                    activity?.finish()
                }
            }
        }
    }

    private fun shareApplicationWithCommunity() {
        try {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,
                "Hey!\nYou know, I have started my training with B Fit application and you won't believe I have started seeing the results already. \nThe workout plans provided in the application is soo easy to go as newbie.\n\nThe diet plan they provide is really effective and the best part about it is that its fully natural and no need to consume extra supplements!\nYou have to try this one out!\n\nDownload the app: https://play.google.com/store/apps/details?id=${requireContext().packageName}")

            startActivity(Intent.createChooser(intent,"Share with"))
        }catch (e: Exception){
            errorToast("Unable to share the app.")
        }
    }

    private fun contactUsSendEmail(
        context: Context,
        emailReceiver: List<String>,
        emailSubject: String,
        emailMessage: String
    ) {
        try {
            val email = Intent(Intent.ACTION_SEND)
            email.type = "plain/text"
            email.putExtra(Intent.EXTRA_EMAIL , emailReceiver.toTypedArray())
            email.putExtra(Intent.EXTRA_SUBJECT , emailSubject)
            email.putExtra(Intent.EXTRA_TEXT , emailMessage)

            context.startActivity(Intent.createChooser(email,"Select any one."))
        }catch (e: Exception){
            errorToast("Unable to reach us.")
        }

    }

    private fun userRecDialogBox(){

        val sharePrefGender: SharedPreferences = context!!.getSharedPreferences("genderCheck", Context.MODE_PRIVATE)
        var checkGenderMale = sharePrefGender.getBoolean("isMaleChecked", false)

        val sharePrefSaveAge : SharedPreferences = context!!.getSharedPreferences("SaveAge",Context.MODE_PRIVATE)
        var userAge = sharePrefSaveAge.getInt("age",0)

        val sharePrefSaveHeight : SharedPreferences = context!!.getSharedPreferences("SaveHt",Context.MODE_PRIVATE)
        var userHt =sharePrefSaveHeight.getInt("height",0)

        val sharePrefSaveWeight : SharedPreferences = context!!.getSharedPreferences("SaveWt", Context.MODE_PRIVATE)
        var userWt = sharePrefSaveWeight.getInt("weight",0)

        val sharePrefSaveBmi : SharedPreferences = context!!.getSharedPreferences("SaveBmi",Context.MODE_PRIVATE)
        var userBmi = sharePrefSaveBmi.getString("bmi","")


        val dialogBinding : DialogUserRecordBinding = DialogUserRecordBinding.inflate(layoutInflater)

        val userRecCustomDialog = Dialog(requireContext())
        userRecCustomDialog.apply {
            setContentView(dialogBinding.root)
            setCancelable(true)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))



            if(checkGenderMale){
                dialogBinding.genderValueTv.text = "Male"
            }else{
                dialogBinding.genderValueTv.text = "Female"
            }
            dialogBinding.ageValueTv.text = userAge.toString()
            dialogBinding.heightValueTv.text = userHt.toString()
            dialogBinding.weightValueTv.text = userWt.toString()
            dialogBinding.bmiValueTv.text = userBmi.toString()

        }.show()

        dialogBinding.userRecCloseBtn.setOnClickListener {
            userRecCustomDialog.dismiss()
        }
    }


    private fun isLoggedIn(){
        //putting value 'false' in the shared preference as the user is route to the splash
        //activity, they will again route to the login fragment as the value now stored is
        //false as if the value stored in the logged in is false then the user will have to
        //log in again

        val sharePrefLogin : SharedPreferences = context!!.getSharedPreferences("login", Context.MODE_PRIVATE)
        var editorlog : SharedPreferences.Editor = sharePrefLogin.edit()
        editorlog.clear()
        editorlog.apply()

    }

    private fun isOnBoarded(){
        //Same goes for the on board activity fragment also
        val sharePrefOnboarded : SharedPreferences = context!!.getSharedPreferences("onBoardCheck", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharePrefOnboarded.edit()
        editor.clear()
        editor.apply()

    }

    private fun resetLevelOfExercise(){
        //Changing the already selected level of exercise to default false
        val sharePrefBeginner : SharedPreferences = context!!.getSharedPreferences("cardBeginner", Context.MODE_PRIVATE)
        var beginnerEditor : SharedPreferences.Editor = sharePrefBeginner.edit()
        beginnerEditor.clear()
        beginnerEditor.apply()


        val sharePrefIntermediate : SharedPreferences = context!!.getSharedPreferences("cardIntermediate", Context.MODE_PRIVATE)
        var intermediateEditor : SharedPreferences.Editor = sharePrefIntermediate.edit()
        intermediateEditor.clear()
        intermediateEditor.apply()


        val sharePrefAdvance : SharedPreferences = context!!.getSharedPreferences("cardAdvance", Context.MODE_PRIVATE)
        var advanceEditor : SharedPreferences.Editor = sharePrefAdvance.edit()
        advanceEditor.clear()
        advanceEditor.apply()

    }

    private fun resetBodyTypeGoal(){
        val sharePrefLooseWeight : SharedPreferences = context!!.getSharedPreferences(
            "cardLooseWeightChecked",
            Context.MODE_PRIVATE
        )
        var looseWeightEditor : SharedPreferences.Editor = sharePrefLooseWeight.edit()
        looseWeightEditor.clear()
        looseWeightEditor.apply()


        val sharePrefBuildMuscle : SharedPreferences = context!!.getSharedPreferences(
            "cardBuildMuscleChecked",
            Context.MODE_PRIVATE
        )
        var buildMuscleEditor : SharedPreferences.Editor = sharePrefBuildMuscle.edit()
        buildMuscleEditor.clear()
        buildMuscleEditor.apply()


        val sharePrefBalance : SharedPreferences = context!!.getSharedPreferences(
            "cardBalanceChecked",
            Context.MODE_PRIVATE
        )
        var balanceEditor : SharedPreferences.Editor = sharePrefBalance.edit()
        balanceEditor.clear()
        balanceEditor.apply()
    }

    private fun resetDietType(){
        val sharePrefVegDiet : SharedPreferences = context!!.getSharedPreferences(
            "cardVegChecked",
            Context.MODE_PRIVATE
        )
        var vegDietEditor : SharedPreferences.Editor = sharePrefVegDiet.edit()
        vegDietEditor.clear()
        vegDietEditor.apply()


        val sharePrefCardNonVeg : SharedPreferences = context!!.getSharedPreferences(
            "cardNonVegChecked",
            Context.MODE_PRIVATE
        )
        var nonVegEditor : SharedPreferences.Editor = sharePrefCardNonVeg.edit()
        nonVegEditor.clear()
        nonVegEditor.apply()


        val sharePrefMixedDiet : SharedPreferences = context!!.getSharedPreferences(
            "cardMixedDietChecked",
            Context.MODE_PRIVATE
        )
        var mixedDietEditor : SharedPreferences.Editor = sharePrefMixedDiet.edit()
        mixedDietEditor.clear()
        mixedDietEditor.apply()
    }

    private fun resetUserDetails(){

        val sharePrefSaveAge : SharedPreferences = context!!.getSharedPreferences("SaveAge",Context.MODE_PRIVATE)
        var ageEditor : SharedPreferences.Editor = sharePrefSaveAge.edit()
        ageEditor.clear()
        ageEditor.apply()

        val sharePrefSaveBmi : SharedPreferences = context!!.getSharedPreferences("SaveBmi",Context.MODE_PRIVATE)
        var bmiEditor : SharedPreferences.Editor = sharePrefSaveBmi.edit()
        bmiEditor.clear()
        bmiEditor.apply()

        val sharePrefSaveHeight : SharedPreferences = context!!.getSharedPreferences("SaveHt",Context.MODE_PRIVATE)
        var htEditor : SharedPreferences.Editor = sharePrefSaveHeight.edit()
        htEditor.clear()
        htEditor.apply()

        val sharePrefSaveWeight : SharedPreferences = context!!.getSharedPreferences("SaveWt", Context.MODE_PRIVATE)
        var wtEditor : SharedPreferences.Editor = sharePrefSaveWeight.edit()
        wtEditor.clear()
        wtEditor.apply()

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

//Privacy policy Url
//https://sites.google.com/pvppcoe.ac.in/b-fit/home