package com.prathameshkumbhar.bfit.loginmodule.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.droidman.ktoasty.KToasty
import com.google.firebase.auth.FirebaseAuth
import com.prathameshkumbhar.bfit.coremodule.InputFilters
import com.prathameshkumbhar.bfit.databinding.FragmentForgotPasswordBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ForgotPasswordFragment : Fragment() {
private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth
    private val navController  by lazy {
        findNavController()
    }
    private val args : ForgotPasswordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForgotPasswordBinding.inflate(inflater,container,false)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.forgotPasswordEmailEditText.setText(args.email)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.forgotPasswordEmailEditText.filters = arrayOf(InputFilters.emailFilter)

        binding.forgotPasswordButton.setOnClickListener {
            val forgotEmail = binding.forgotPasswordEmailEditText.text.toString().trim()
            if(forgotEmail.isNotEmpty()){
                firebaseAuth.sendPasswordResetEmail(forgotEmail)
                    .addOnCompleteListener{
                        if (it.isSuccessful) {
                            KToasty.success(
                                requireContext(),
                                "Email sent successfully, Check the mail box !",
                                Toast.LENGTH_SHORT
                            ).show()
                            lifecycleScope.launch{
                                delay(1000)
                                navController.popBackStack()
                            }
                        }else{
                            KToasty.error(
                                requireContext(),
                                it.exception.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }else{
                KToasty.error(
                    requireContext(),
                    "Empty fields are not allowed !",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}