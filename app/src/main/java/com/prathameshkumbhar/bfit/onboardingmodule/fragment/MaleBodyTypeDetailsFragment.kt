package com.prathameshkumbhar.bfit.onboardingmodule.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.prathameshkumbhar.bfit.databinding.FragmentMaleBodyTypeDetailsBinding

class MaleBodyTypeDetailsFragment : Fragment() {

    private var _binding: FragmentMaleBodyTypeDetailsBinding? = null
    private val binding get() = _binding!!
    private val navController  by lazy {
        findNavController()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMaleBodyTypeDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController.navigate(
            MaleBodyTypeDetailsFragmentDirections.actionMaleBodyTypeDetailsFragmentToMaleLevelOfDailyActivityFragment())

    }

}