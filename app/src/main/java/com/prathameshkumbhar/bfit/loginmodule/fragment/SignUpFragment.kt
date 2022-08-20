package com.prathameshkumbhar.bfit.loginmodule.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.droidman.ktoasty.KToasty
import com.droidman.ktoasty.showSuccessToast
import com.google.firebase.auth.FirebaseAuth
import com.prathameshkumbhar.bfit.coremodule.InputFilters
import com.prathameshkumbhar.bfit.databinding.FragmentSignupBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {
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
            //Go to signIn frag using navigation
            navController.popBackStack()
        }

        binding.signUpButton.setOnClickListener {
            val userName = binding.signUpNameEditText.text.toString()
            val userPhoneNum = binding.signUpPhoneNumberEditText.text.toString().trim()
            val email = binding.signUpEmailEditText.text.toString().trim()
            val pass = binding.signUpPassEditText.text.toString().trim()
            val confirmPass = binding.signUpConfirmPassEditText.text.toString().trim()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty() && userName.isNotEmpty() && userPhoneNum.isNotEmpty()) {
                if (pass.length >= 8) {
                    if (pass == confirmPass) {
                        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    requireContext().showSuccessToast("Successfully Signed Up!")
                                    lifecycleScope.launch {
                                        delay(1000)
                                        //Go to signIn frag using navigation
                                        navController.navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment2(email , pass ))
                                        navController.popBackStack()

                                    }
                                } else {
                                    KToasty.info(
                                        requireContext(),
                                        it.exception.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    } else {
                        KToasty.warning(
                            requireContext(),
                            "Password does not match !",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    KToasty.info(
                        requireContext(),
                        "Password must not be less than 8 Characters !",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }else{
                KToasty.warning(
                    requireContext(),
                    "Empty fields are not allowed !",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}