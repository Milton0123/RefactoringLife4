package com.example.refactoringlife4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import com.example.refactoringlife4.databinding.ActivityLoginMailBinding

class LoginMailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginMailBinding
    var viewModel = LoginMailViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginMailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClick()
        observer()
        action()
    }
    private fun action(){
        validateLoginFirebase()
    }
    private fun observer(){
        viewModel.emailData.observe(this){
            binding.bnEnterLogin.isEnabled = it
            if(it){binding.bnEnterLogin.setBackgroundResource(R.color.black)}
            if(!it){binding.bnEnterLogin.setBackgroundResource(R.color.gray_off)}
        }
        viewModel.passData.observe(this){
            binding.bnEnterLogin.isEnabled = it
            if(it){binding.bnEnterLogin.setBackgroundResource(R.color.black)}
            if(!it){binding.bnEnterLogin.setBackgroundResource(R.color.gray_off)}
        }
    }
    private fun setOnClick(){
        binding.bnEnterLogin.setOnClickListener{

        }
    }

    private fun validateLoginFirebase(){
        binding.etEmail.doAfterTextChanged{
            viewModel.checkLoginEmail(it.toString())
        }
        binding.etPassword.doAfterTextChanged{
            viewModel.checkLoginPass(it.toString())
        }

    }
}