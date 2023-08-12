package com.example.refactoringlife4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import com.example.refactoringlife4.databinding.ActivityLoginMailBinding
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class LoginMailActivity : AppCompatActivity() {
    val fireBaseResponse = FirebaseService()
    private lateinit var binding: ActivityLoginMailBinding
    private lateinit var viewModel: LoginMailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginMailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observer()
        onClick()
        action()
    }

    private fun action() {
        validateLoginFirebase()
    }

    private fun observer() {
        viewModel = ViewModelProvider(this).get(LoginMailViewModel::class.java)
        viewModel.responseLiveData.observe(this) {
            //en esta parte en base a las respuestas que se obtengan se deben de mostrar las respuestas
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
        viewModel.checkUser.observe(this) {
            binding.bnEnterLogin.isEnabled = it
            if (it) {
                binding.bnEnterLogin.setBackgroundResource(R.color.black)
            }
            if (!it) {
                binding.bnEnterLogin.setBackgroundResource(R.color.gray_off)
            }
        }
    }


    private fun validateLoginFirebase() {
        binding.etEmail.doAfterTextChanged {
            val pass = binding.etPassword.toString()
            viewModel.checkUserValidation(it.toString(),pass)
        }
        binding.etPassword.doAfterTextChanged {
            val email = binding.etEmail.toString()
            viewModel.checkUserValidation(email,it.toString())
        }

    }

    private fun onClick() {
        binding.ivBackLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.bnEnterLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            lifecycleScope.launch {
                var responseStatus = fireBaseResponse.login(email, password)
                viewModel.status(responseStatus)
            }
        }
    }
}



