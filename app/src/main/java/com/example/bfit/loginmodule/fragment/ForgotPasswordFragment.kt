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
import com.example.bfit.coremodule.InputFilters
import com.example.bfit.databinding.FragmentForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ForgotPasswordFragment : Fragment() {
//    Today's task to complete the forgot password fragment + signIn and SignUp fragment validations.
private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth
    private val navController  by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForgotPasswordBinding.inflate(inflater,container,false)
        firebaseAuth = FirebaseAuth.getInstance()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.forgotEmailEditText.filters = arrayOf(InputFilters.emailFilter)

        binding.forgotPasswordButton.setOnClickListener {
            val forgotEmail = binding.forgotEmailEditText.text.toString().trim()
            if(forgotEmail.isNotEmpty()){
                firebaseAuth.sendPasswordResetEmail(forgotEmail)
                    .addOnCompleteListener{
                        if (it.isSuccessful) {
                            KToasty.success(requireContext(),"Email sent successfully !", Toast.LENGTH_SHORT).show()
                            lifecycleScope.launch{
                                delay(1000)
                                navController.navigate(
                                    ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment()
                                )
                                navController.popBackStack()
                            }
                        }else{
                            KToasty.error(requireContext(), it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }


    }
}