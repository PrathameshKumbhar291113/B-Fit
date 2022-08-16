package com.example.bfit.onboardingmodule.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.bfit.databinding.FragmentGenderBinding

class GenderFragment : Fragment() {
    private var _binding: FragmentGenderBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenderBinding.inflate(inflater,container,false)

        binding.maleImg.load("https://i.imgur.com/5epNbBi.png"){
            crossfade(true)
        }
        binding.femaleImg.load("https://i.imgur.com/5epNbBi.png"){
            crossfade(true)
        }
        return binding.root
    }
}