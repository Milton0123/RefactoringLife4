package com.example.refactoringlife4.ui.loginFireStore.presenters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import android.widget.Toast
import com.example.refactoringlife4.databinding.ActivityLoginFireStoreBinding
import com.example.refactoringlife4.ui.login.LoginActivity
import com.example.refactoringlife4.ui.loginFireStore.viewmodel.LoginFireStoreViewModel
import com.example.refactoringlife4.ui.loginFireStore.viewmodel.LoginFireStoreViewModelEvent
import com.example.refactoringlife4.ui.loginFireStore.viewmodel.LoginFireStoreViewModelFactory

class LoginFireStoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginFireStoreBinding
    private lateinit var viewModel: LoginFireStoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginFireStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        validateFields()
        getViewModel()
        observer()
        onClick()

    }

    private fun getViewModel() {
        viewModel = LoginFireStoreViewModelFactory().create(LoginFireStoreViewModel::class.java)
    }

    private fun observer() {

        viewModel.data.observe(this) {
            when (it) {
                is LoginFireStoreViewModelEvent.ShowSuccessView -> {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                }
                is LoginFireStoreViewModelEvent.ShowModalError -> {
                    Toast.makeText(this, it.modalDialog.description, Toast.LENGTH_SHORT).show()
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
                email.toString().trim(),
                binding.etPassword.text.toString().trim()
            )
        }
        binding.etPassword.doAfterTextChanged { pass ->
            viewModel.checkAllFields(
                binding.etEmail.text.toString().trim(),
                pass.toString().trim()
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
