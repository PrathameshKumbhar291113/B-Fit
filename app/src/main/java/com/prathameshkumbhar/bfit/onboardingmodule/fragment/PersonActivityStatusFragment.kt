package com.prathameshkumbhar.bfit.onboardingmodule.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prathameshkumbhar.bfit.databinding.FragmentPersonActivityStatusBinding
import com.prathameshkumbhar.bfit.mainmodule.HomeActivity
import splitties.fragments.start

class PersonActivityStatusFragment : Fragment() {
    private var _binding : FragmentPersonActivityStatusBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPersonActivityStatusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharePrefOnboarded : SharedPreferences = context!!.getSharedPreferences("onBoardCheck", Context.MODE_PRIVATE)

        binding.personActivityTitleTv.setOnClickListener {
            start<HomeActivity>(){

                var editor: SharedPreferences.Editor = sharePrefOnboarded.edit()
                editor.putBoolean("isOnboardComplete",true)
                editor.apply()

                activity?.finish()
            }
        }
    }
}