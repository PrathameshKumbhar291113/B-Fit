package com.example.bfit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bfit.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {
    private var _binding : FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater,container,false)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.alreadyRegisterSignInTextView.setOnClickListener {
            //Go to signIn frag using navigation
        }

        binding.signUpButton.setOnClickListener {
            val email = binding.signUpEmailEditText.text.toString()
            val pass = binding.signUpPassEditText.text.toString()
            val confirmPass = binding.signUpConfirmPassEditText.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if(email.endsWith("gmail.com") || email.endsWith("ac.in")){
                    if(pass == confirmPass){
                        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                            if (it.isSuccessful){
                                //Go to signIn frag using navigation
                            }else{
                                Toast.makeText(requireContext(),"Password Entered Is Incorrect !", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else{
                        Toast.makeText(requireContext(), "Password does not match", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(requireContext(), "Only gmail.com & ac.in extension is valid !", Toast.LENGTH_SHORT).show()                }
            }else{
                Toast.makeText(requireContext(), "Empty Field not allowed !", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}