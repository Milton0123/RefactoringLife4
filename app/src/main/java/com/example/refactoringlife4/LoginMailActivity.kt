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
    private val fireBaseResponse = UserFirebaseService()
    private lateinit var binding: ActivityLoginMailBinding
    private lateinit var viewModel: LoginMailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginMailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        validateFields()
        initViewModel()
        observer()
        onClick()

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(LoginMailViewModel::class.java)
    }

    private fun observer() {

        viewModel.data.observe(this) {
            when (it) {
                is UserEvent.ShowSuccessView -> {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }
                is UserEvent.ShowModalError -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.validFields.observe(this) {
            binding.btEnterLogin.isEnabled = it
        }
    }


    private fun validateFields() {
        binding.etEmail.doAfterTextChanged { email ->
            viewModel.checkAllFields(
                email.toString(),
                binding.etPassword.text.toString()
            )
        }
        binding.etPassword.doAfterTextChanged { pass ->
            viewModel.checkAllFields(
                binding.etEmail.text.toString(),
                pass.toString()
            )
        }
    }

    private fun onClick() {
        binding.ivBackLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.btEnterLogin.setOnClickListener {
            viewModel.loginUser(binding.etPassword.text.toString(), binding.etEmail.text.toString())
        }
    }
}



