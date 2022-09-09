package com.prathameshkumbhar.bfit.mainmodule.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.droidman.ktoasty.showSuccessToast
import com.google.firebase.auth.FirebaseAuth
import com.prathameshkumbhar.bfit.coremodule.SplashActivity
import com.prathameshkumbhar.bfit.databinding.FragmentProfileBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.fragments.start


class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth : FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)

        firebaseAuth = FirebaseAuth.getInstance()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logoutButton.setOnClickListener {

            val sharePrefLogin : SharedPreferences = context!!.getSharedPreferences("login", Context.MODE_PRIVATE)
            var editorlog : SharedPreferences.Editor = sharePrefLogin.edit()
            editorlog.putBoolean("flag",false)
            editorlog.apply()

            requireContext().showSuccessToast("Successfully Logged Out!")
            firebaseAuth.signOut()
            lifecycleScope.launch(){
                delay(1000)
                start<SplashActivity>()
            }
        }
    }
}