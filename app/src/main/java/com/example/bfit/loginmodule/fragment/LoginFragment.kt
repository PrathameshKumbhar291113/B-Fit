package com.example.bfit.loginmodule.fragment

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.droidman.ktoasty.KToasty
import com.example.bfit.coremodule.InputFilters
import com.example.bfit.databinding.FragmentLoginBinding
import com.example.bfit.onboardingmodule.OnboardActivity
import com.google.firebase.auth.FirebaseAuth
import splitties.fragments.start

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth
    private val navController  by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginEmailEditText.filters = arrayOf(InputFilters.emailFilter)
        binding.notRegisteredSignUpTextView.setOnClickListener {
            //Go to signUp Fragment
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())

        }

        binding.signInButton.setOnClickListener {
            val email = binding.loginEmailEditText.text.toString()
            val pass = binding.loginPassEditText.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                if (email.endsWith("gmail.com") || email.endsWith("ac.in")) {
                    firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            //go to OnboardActivity\
                            KToasty.success(requireContext(), "Successfully SignIn!")
                            start<OnboardActivity>()
                        } else {
                            KToasty.warning(requireContext(),"Please check the email or password you have entered!",Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    KToasty.warning(requireContext(), "Only gmail.com & ac.in extension is valid!", Toast.LENGTH_SHORT).show()
                }
            } else {
                KToasty.warning(requireContext(), "Empty Field not allowed !", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signInForgotPassTextView.setOnClickListener {
            //Go to forgot pass fragment
//            it.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment(email = null))
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment(email = null))
        }
    }
}