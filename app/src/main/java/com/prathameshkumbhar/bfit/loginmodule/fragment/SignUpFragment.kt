package com.prathameshkumbhar.bfit.loginmodule.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.prathameshkumbhar.bfit.coremodule.BaseFragment
import com.prathameshkumbhar.bfit.coremodule.InputFilters
import com.prathameshkumbhar.bfit.databinding.FragmentSignupBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class SignUpFragment : BaseFragment() {
    private var _binding : FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth
    private val navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater,container,false)
        firebaseAuth = FirebaseAuth.getInstance()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpEmailEditText.filters = arrayOf(InputFilters.emailFilter)
        binding.alreadyRegisterSignInTextView.setOnClickListener {
            if (checkForInternet(requireContext())){
                navController.popBackStack()
            }else{
                errorToast("Not connected to Internet. Please connect to internet service to proceed further !")
            }
            //Go to signIn frag using navigation

        }

        binding.signUpButton.setOnClickListener {
            var userName = binding.signUpNameEditText.text.toString()
            val userPhoneNum = binding.signUpPhoneNumberEditText.text.toString().trim()
            val email = binding.signUpEmailEditText.text.toString().trim()
            val pass = binding.signUpPassEditText.text.toString().trim()
            val confirmPass = binding.signUpConfirmPassEditText.text.toString().trim()
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            val phoneNumPattern = "^[5-9]{1}[0-9]{9}\$"

            if (checkForInternet(requireContext())){
                if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty() && userName.isNotEmpty() && userPhoneNum.isNotEmpty()) {
                    if(email.matches(emailPattern.toRegex()) && userPhoneNum.matches(phoneNumPattern.toRegex())){
                        if (pass.length >= 8) {
                            if (pass == confirmPass) {

                                firebaseAuth.createUserWithEmailAndPassword(email, pass)
                                    .addOnCompleteListener {
                                        if (it.isSuccessful) {
                                            //Setting the user name when doing sign up
                                            setUserName(userName)
                                            binding.scrollView4.visibility = View.GONE
                                            successToast("Registered Successfully !")

                                            lifecycleScope.launch {
                                                binding.progressBar.visibility = View.VISIBLE
                                                delay(2000)
                                                //Go to signIn frag using navigation
                                                navController.navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment2(email, pass))
                                                navController.popBackStack()
                                            }
                                        } else {
                                            errorToast("${it.exception?.message}")
                                        }
                                    }
                            } else {
                                warningToast("Password does not match !")
                            }
                        }else {
                            infoToast("Password must not be less than 8 Characters !")
                        }
                    }else{
                        warningToast("Email / Phone Number is Invalid !")
                    }
                }
                else{
                    warningToast("Empty fields are not allowed !")
                }
            }else{
                errorToast("Not connected to Internet. Please connect to internet service to proceed further !")
            }
        }

    }

    private fun setUserName(name : String){
        var profileUpdate = userProfileChangeRequest {
            displayName = name
        }
        firebaseAuth.currentUser!!.updateProfile(profileUpdate)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Timber.d("Successfully updated the profile")
                }
            }
    }
}