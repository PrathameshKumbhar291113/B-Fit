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
import com.droidman.ktoasty.showSuccessToast
import com.google.firebase.auth.FirebaseAuth
import com.prathameshkumbhar.bfit.coremodule.InputFilters
import com.prathameshkumbhar.bfit.databinding.FragmentLoginBinding
import com.prathameshkumbhar.bfit.onboardingmodule.OnboardActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.fragments.start

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth
    private val navController  by lazy {
        findNavController()
    }
    private val args : LoginFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.loginEmailEditText.setText(args.email)
        binding.loginPassEditText.setText(args.password)



        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginEmailEditText.filters = arrayOf(InputFilters.emailFilter)
        binding.notRegisteredSignUpTextView.setOnClickListener {
            //Go to signUp Fragment
            navController.navigate(
                LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            )

        }

        binding.signInButton.setOnClickListener {
            val email = binding.loginEmailEditText.text.toString().trim()
            val pass = binding.loginPassEditText.text.toString().trim()


            if (email.isNotEmpty() && pass.isNotEmpty()) {
                    if(pass.length >= 8) {
                        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                            if (it.isSuccessful) {
                                requireContext().showSuccessToast("Successfully Signed In!")
                                lifecycleScope.launch {
                                    delay(1000)
                                    //go to OnboardActivity
                                    start<OnboardActivity>(){
                                        activity?.finish()
                                    }
                                }
                            } else {
                                KToasty.info(requireContext(),it.exception.toString(),Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else{
                        KToasty.info(requireContext(),"Password must not be less than 8 Characters !",Toast.LENGTH_SHORT).show()
                    }
                } else {
                KToasty.warning(requireContext(), "Empty fields are not allowed !", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signInForgotPassTextView.setOnClickListener {
            val email = binding.loginEmailEditText.text.toString().trim()
            //Go to forgot pass fragment
//            it.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment(email = null))
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment(email= ""))
        }
    }
}