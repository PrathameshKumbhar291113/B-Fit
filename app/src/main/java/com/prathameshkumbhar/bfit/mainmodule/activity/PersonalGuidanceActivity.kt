package com.prathameshkumbhar.bfit.mainmodule.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.droidman.ktoasty.KToasty
import com.prathameshkumbhar.bfit.databinding.ActivityPersonalGuidanceBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PersonalGuidanceActivity : AppCompatActivity() {
    lateinit var binding: ActivityPersonalGuidanceBinding
    var flag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPersonalGuidanceBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.getAppointmentBtn.setOnClickListener {

            val userName = binding.userNameEditText.text.toString().trim()
            val userMob = binding.userMobEditText.text.toString().trim()
            val userState = binding.userStateEditText.text.toString().trim()
            val userTown = binding.userTownEditText.text.toString().trim()
            val userQuery = binding.userQueryEditText.text.toString()
            val phoneNumPattern = "^[5-9]{1}[0-9]{9}\$"

            if (userName.isNotEmpty() && userMob.isNotEmpty() && userState.isNotEmpty() && userTown.isNotEmpty() && userQuery.isNotEmpty() ){
                if(userMob.matches(phoneNumPattern.toRegex())){


                    lifecycleScope.launch{
                        delay(1500)
                    }

                    appointmentSendEmail(this, listOf("bfit.dietician1311@gmail.com"),"Appointment from dietician.","I need appointment regarding diet,Below are my details:\n Name: $userName\n Mobile Num: $userMob\nLocation: $userState / $userTown \nQuery: $userQuery")
                    flag = true


                }else{
                    KToasty.warning(this,"Phone number is invalid!").show()
                }

            }else{
                KToasty.warning(this,"Empty Fields are not allowed!").show()
            }
        }

    }


    private fun appointmentSendEmail(
        context: Context,
        emailReceiver: List<String>,
        emailSubject: String,
        emailMessage: String
    ) {

        val email = Intent(Intent.ACTION_SEND)
        email.type = "plain/text"
        email.putExtra(Intent.EXTRA_EMAIL , emailReceiver.toTypedArray())
        email.putExtra(Intent.EXTRA_SUBJECT , emailSubject)
        email.putExtra(Intent.EXTRA_TEXT , emailMessage)

        context.startActivity(Intent.createChooser(email,"Select any one."))
    }

    override fun onRestart() {

        super.onRestart()

        lifecycleScope.launch {
            if (flag){
                KToasty.success(baseContext,"Our team will connect to you within 2 working days on successful email received.").show()

                binding.userNameEditText.text = null
                binding.userMobEditText.text = null
                binding.userStateEditText.text = null
                binding.userTownEditText.text = null
                binding.userQueryEditText.text = null

                delay(3000)

                finish()
            }

        }

    }
}