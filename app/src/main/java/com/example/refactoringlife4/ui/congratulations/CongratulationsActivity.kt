package com.example.refactoringlife4.ui.congratulations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.refactoringlife4.databinding.ActivityCongratulationsBinding

class CongratulationsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCongratulationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCongratulationsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}