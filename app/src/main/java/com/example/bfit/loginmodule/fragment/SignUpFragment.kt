package com.example.bfit.loginmodule.fragment

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
import com.example.bfit.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
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

        binding.alreadyRegisterSignInTextView.setOnClickListener {
            //Go to signIn frag using navigation
            navController.navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment2())
        }

        binding.signUpButton.setOnClickListener {
            val userName = binding.signUpNameEditText.text.toString()
            val email = binding.signUpEmailEditText.text.toString()
            val pass = binding.signUpPassEditText.text.toString()
            val confirmPass = binding.signUpConfirmPassEditText.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if(email.endsWith("gmail.com") || email.endsWith("ac.in")){
                    if(pass == confirmPass){
                        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                            if (it.isSuccessful){
                                requireContext().showSuccessToast("Successfully Signed Up!")
                                lifecycleScope.launch {
                                    delay(1000)
                                    //Go to signIn frag using navigation
                                    navController.navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment2())
                                    navController.popBackStack()

                                }
                            }else{
                                KToasty.warning(requireContext(),"Password Entered Is Incorrect !", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else{
                        KToasty.warning(requireContext(), "Password does not match", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    KToasty.warning(requireContext(), "Only gmail.com & ac.in extension is valid !", Toast.LENGTH_SHORT).show()                }
            }else{
                KToasty.warning(requireContext(), "Empty fields are not allowed !", Toast.LENGTH_SHORT).show()
            }
        }

    }
}