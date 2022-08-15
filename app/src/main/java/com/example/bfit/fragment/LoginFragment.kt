package com.example.bfit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bfit.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.notRegisteredSignUpTextView.setOnClickListener {
            //Go to signUp Fragment
        }

        binding.signInButton.setOnClickListener {
            val email = binding.loginEmailEditText.text.toString()
            val pass = binding.loginPassEditText.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                if (email.endsWith("gmail.com") || email.endsWith("ac.in")) {
                    firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            //go to OnboardActivity
                        } else {
                            Toast.makeText(requireContext(), it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Only gmail.com & ac.in extension is valid !", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Empty Field not allowed !", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signInForgotPassTextView.setOnClickListener {
            //Go to forgot pass fragment
        }

        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        start<OnboardActivity>()
//    }

}