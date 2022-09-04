package com.prathameshkumbhar.bfit.onboardingmodule.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.prathameshkumbhar.bfit.databinding.FragmentMaleHeightWeightBinding

class MaleHeightWeightFragment : Fragment() {
    private var _binding : FragmentMaleHeightWeightBinding? = null
    private val binding get() = _binding!!

    private val navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMaleHeightWeightBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView3.setOnClickListener {
            navController.navigate(
                MaleHeightWeightFragmentDirections.actionMaleHeightWeightFragmentToMaleBodyTypeDetailsFragment()
            )
        }
    }

}