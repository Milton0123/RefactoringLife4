package com.example.refactoringlife4.ui.termAndCondition.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.refactoringlife4.databinding.FragmentTermsAndConditionBinding
import com.example.refactoringlife4.utils.Utils

class TermsAndConditionFragment : Fragment() {
    private lateinit var binding: FragmentTermsAndConditionBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTermsAndConditionBinding.inflate(inflater, container, false)
        Onclick()
        return binding.root
    }

    private fun Onclick() {
        binding.btBackBlackTermsAndConditions.setOnClickListener {
            binding.btBackBlackTermsAndConditions.isEnabled = false

            binding.btBackBlackTermsAndConditions.animate().apply {
                translationX(300f)
                interpolator = AccelerateDecelerateInterpolator()
                duration = 1000
                withEndAction {
                    findNavController().popBackStack()
                    binding.btBackBlackTermsAndConditions.isEnabled = true
                }
            }
        }

    }


}
