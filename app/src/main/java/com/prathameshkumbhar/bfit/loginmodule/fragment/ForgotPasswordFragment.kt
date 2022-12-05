package com.prathameshkumbhar.bfit.loginmodule.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.prathameshkumbhar.bfit.coremodule.BaseFragment
import com.prathameshkumbhar.bfit.coremodule.InputFilters
import com.prathameshkumbhar.bfit.databinding.FragmentForgotPasswordBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ForgotPasswordFragment : BaseFragment() {
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
            if (checkForInternet(requireContext())){
                val forgotEmail = binding.forgotPasswordEmailEditText.text.toString().trim()
                val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
                if(forgotEmail.isNotEmpty()){
                    if (forgotEmail.matches(emailPattern.toRegex())){
                        firebaseAuth.sendPasswordResetEmail(forgotEmail)
                            .addOnCompleteListener{
                                if (it.isSuccessful) {
                                    binding.scrollView2.visibility = View.GONE
                                    successToast("Email sent successfully, Check the mail box!")
                                    lifecycleScope.launch{
                                        binding.progressBar.visibility = View.VISIBLE
                                        delay(2000)
                                        navController.popBackStack()
                                    }
                                }else{
                                    errorToast("${it.exception?.message}")

                                }
                            }

                    }else{
                        warningToast("Email is invalid !")
                    }
                }else{
                    warningToast("Empty fields are not allowed !")
                }

            }else{

                errorToast("Not connected to Internet. Please connect to internet service to reset the password !")

            }

        }
    }
}