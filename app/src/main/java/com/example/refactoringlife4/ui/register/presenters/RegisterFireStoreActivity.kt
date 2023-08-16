package com.example.refactoringlife4.ui.register.presenters

import androidx.lifecycle.ViewModelProvider
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.refactoringlife4.ui.login.LoginActivity
import com.example.refactoringlife4.ui.register.viewmodel.RegisterFireStoreViewModel
import com.example.refactoringlife4.databinding.ActivityRegisterFireStoreBinding
import com.example.refactoringlife4.ui.loginFireStore.viewmodel.LoginViewModelEvent


class RegisterFireStoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterFireStoreBinding
    private lateinit var viewModel: RegisterFireStoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterFireStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupTextObserver()
        initViewModel()
        observer()
        onClick()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(RegisterFireStoreViewModel::class.java)
    }

    private fun observer() {

        viewModel.data.observe(this) {
            when (it) {
                is LoginViewModelEvent.ShowSuccessView -> {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }
                is LoginViewModelEvent.ShowModalError -> {
                    Toast.makeText(this, it.description, Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.validFields.observe(this) {
            binding.btRegister.isEnabled = it
        }
    }

    fun onClick() {

        binding.ivRegisterBack.setOnClickListener {
            goToBack()
        }

        binding.btRegister.setOnClickListener {
            viewModel.registerUser(
                binding.etRegisterEmail.text.toString(),
                binding.etRegisterName.text.toString(),
                binding.etRegisterPassword.text.toString()
            )
        }

    }

    private fun goToBack() {
        binding.ivRegisterBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupTextObserver() {
        binding.etRegisterName.doAfterTextChanged {
            viewModel.validateFields(
                binding.etRegisterEmail.text.toString().trim(),
                binding.etRegisterPassword.text.toString().trim(),
                it.toString().trim()
            )
        }

        binding.etRegisterEmail.doAfterTextChanged {
            viewModel.validateFields(
                it.toString().trim(),
                binding.etRegisterPassword.text.toString().trim(),
                binding.etRegisterName.text.toString().trim()
            )
        }

        binding.etRegisterPassword.doAfterTextChanged {
            viewModel.validateFields(
                it.toString().trim(),
                binding.etRegisterEmail.text.toString().trim(),
                binding.etRegisterName.text.toString().trim()
            )
        }
    }
}
