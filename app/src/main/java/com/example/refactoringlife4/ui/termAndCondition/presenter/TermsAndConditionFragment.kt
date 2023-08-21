package com.example.refactoringlife4.ui.termAndCondition.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.refactoringlife4.databinding.FragmentTermsAndConditionBinding

class TermsAndConditionFragment : Fragment() {
   private lateinit var binding: FragmentTermsAndConditionBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTermsAndConditionBinding.inflate(inflater,container, false)

        return binding.root
    }
}
